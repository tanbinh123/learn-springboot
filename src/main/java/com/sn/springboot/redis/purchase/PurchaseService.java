package com.sn.springboot.redis.purchase;

import com.sn.springboot.dao.PurchaseDao;
import com.sn.springboot.pojo.Product;
import com.sn.springboot.pojo.PurchaseRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 * 作者：SheHuan
 * 时间：2020/10/22 10:38
 */
@Service
public class PurchaseService {
    @Autowired
    private PurchaseDao purchaseDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // redis中记录商品购买记录list的key的前缀（后边会拼接商品id）
    private static final String PURCHASE_RECORD_LIST = "purchase_record_list_";

    // redis中记录购买商品id的set的key
    private static final String PRODUCT_ID_SET = "product_id_set";

    // 32位SHA1编码，将脚本缓存到redis服务器返回的
    private String sha1;

    // 抢购商品的Lua脚本
    private final String purchaseScript = ""
            // 用set保存产品编号
            + "redis.call('sadd', KEYS[1], ARGV[2]) \n"
            // redis中当前商品购买记录list的key
            + "local purchaseRecordListKey = KEYS[2]..ARGV[2] \n"
            // 用户id
            + "local userId = ARGV[1] \n"
            // 购买商品的key
            + "local product = 'product_'..ARGV[2] \n"
            // 购买数量
            + "local quantity = tonumber(ARGV[3]) \n"
            // 当前库存（获取redis中key为product的map中key为stock的值）
            + "local stock = tonumber(redis.call('hget', product, 'stock')) \n"
            // 商品单价
            + "local price = tonumber(redis.call('hget', product, 'price')) \n"
            // 购买日期
            + "local purchaseDate = ARGV[4] \n"
            // 开始判断库存，如果库存不足返回0，购买失败
            + "if stock < quantity then return 0 end \n"
            // 减库存
            + "stock = stock - quantity \n"
            // 更新库存记录（设置redis中key为product的map中key为stock的值）
            + "redis.call('hset', product, 'stock', tostring(stock)) \n"
            // 计算总价格
            + "local sum = price * quantity \n"
            // 拼接购买记录数据（用户id,单价,购买数量,总价,购买日期）
            + "local purchaseRecord = userId..','..price..','..quantity..','..sum..','..purchaseDate \n"
            // 将购买记录插入到list
            + "redis.call('rpush', purchaseRecordListKey, purchaseRecord) \n"
            // 返回1，后买成功
            + "return 1 \n";

    /**
     * 使用redis处理抢购业务
     *
     * @param userId
     * @param productId
     * @param quantity
     * @return
     */
    public boolean purchaseByRedis(long userId, long productId, int quantity) {
        // 购买日期
        long purchaseDate = System.currentTimeMillis();
        Jedis jedis = null;

        try {
            // 获取原始连接
            jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
            // 缓存脚本
            if (sha1 == null) {
                sha1 = jedis.scriptLoad(purchaseScript);
            }
            // 执行脚本
            // 参数2表示可变参数的前两个是key，在Lua脚本中用KEYS[index]获取key，ARGV[index]获取value，index从1开始
            Long result = (Long) jedis.evalsha(sha1, 2, PRODUCT_ID_SET, PURCHASE_RECORD_LIST, userId + "", productId + "", quantity + "", purchaseDate + "");
            return result == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            // 关闭jedis连接
            if (jedis != null && jedis.isConnected()) {
                jedis.close();
            }
        }
    }

    /**
     * 开启新事务执行
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void dealRedisPurchase(List<PurchaseRecord> prList) {
        for (PurchaseRecord pr : prList) {
            // 添加购买记录
            purchaseDao.addPurchaseRecord(pr);
            // 减库存
            purchaseDao.decreaseProductStock(pr.getProductId(), pr.getQuantity());
        }
    }

    /**
     * 使用乐观锁
     *
     * @param userId
     * @param productId
     * @param quantity
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean purchase(long userId, long productId, int quantity) {
        // 尝试重入三次
        for (int i = 0; i < 3; i++) {
            Product product = purchaseDao.getProductById(productId);
            // 库存不足
            if (quantity > product.getStock()) {
                return false;
            }
            // 当前版本号
            Integer version = product.getVersion();
            // 减库存
            int result = purchaseDao.decreaseProductStock2(productId, quantity, version);
            // 减库存失败
            if (result == 0) {
                continue;
            }
            // 初始化购买记录
            PurchaseRecord purchaseRecord = initPurchaseRecord(userId, product, quantity);
            purchaseDao.addPurchaseRecord(purchaseRecord);
            return true;
        }
        return false;
    }

    private PurchaseRecord initPurchaseRecord(Long userId, Product product, Integer quantity) {
        PurchaseRecord purchaseRecord = new PurchaseRecord();
        purchaseRecord.setUserId(userId);
        purchaseRecord.setPrice(product.getPrice());
        purchaseRecord.setQuantity(quantity);
        purchaseRecord.setSum(product.getPrice() * quantity);
        purchaseRecord.setPurchaseTime(new Date());
        purchaseRecord.setProductId(product.getId());
        return purchaseRecord;
    }
}
