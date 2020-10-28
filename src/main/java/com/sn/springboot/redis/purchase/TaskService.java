package com.sn.springboot.redis.purchase;

import com.sn.springboot.pojo.PurchaseRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 功能：
 * 作者：SheHuan
 * 时间：2020/10/22 10:38
 */
@Service
public class TaskService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private PurchaseService purchaseService;

    // redis中记录商品购买记录list的key的前缀（后边会拼接商品id）
    private static final String PURCHASE_RECORD_LIST = "purchase_record_list_";

    // redis中记录购买商品id的set的key
    private static final String PRODUCT_ID_SET = "product_id_set";

    // 每次取出1000条，避免消耗过多内存
    private static final int ONE_TIME_SIZE = 1000;

    /**
     * 处理redis缓存数据，来更新数据库
     */
    @Scheduled(initialDelay = 60 * 1000, fixedDelay = 60 * 1000)
    public void purchaseTask() {
        // 获取抢购商品ID的set
        Set<String> productIdSet = stringRedisTemplate.opsForSet().members(PRODUCT_ID_SET);
        List<PurchaseRecord> purchaseRecordList = new ArrayList<>();
        for (String productIdStr : productIdSet) {
            Long productId = Long.valueOf(productIdStr);
            // 拼接对应商品购买记录list的key
            String purchaseRecordListKey = PURCHASE_RECORD_LIST + productId;
            BoundListOperations<String, String> purchaseRecordListOps = stringRedisTemplate.boundListOps(purchaseRecordListKey);
            // 购买记录条数
            long size = purchaseRecordListOps.size();
            // 每次取1000条，计算需要取的次数
            long times = size % ONE_TIME_SIZE == 0 ? size / ONE_TIME_SIZE : size / ONE_TIME_SIZE + 1;
            for (int i = 0; i < times; i++) {
                List<String> purchaseRecordStrList = null;
                // 每次取数据的开始索引
                long start;
                if (i == 0) {
                    start = i * ONE_TIME_SIZE;
                } else {
                    start = i * ONE_TIME_SIZE + 1;
                }
                purchaseRecordStrList = purchaseRecordListOps.range(start, (i + 1) * ONE_TIME_SIZE);
                for (String purchaseRecordStr : purchaseRecordStrList) {
                    // 解析购买记录字符串
                    PurchaseRecord purchaseRecord = createPurchaseRecord(productId, purchaseRecordStr);
                    purchaseRecordList.add(purchaseRecord);
                }
                try {
                    purchaseService.dealRedisPurchase(purchaseRecordList);
                    // 清除记录
                    purchaseRecordList.clear();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            stringRedisTemplate.delete(purchaseRecordListKey);
            stringRedisTemplate.opsForSet().remove(PRODUCT_ID_SET, productIdStr);
        }
    }

    private PurchaseRecord createPurchaseRecord(Long productId, String purchaseRecordStr) {
        String[] records = purchaseRecordStr.split(",");
        Long userId = Long.parseLong(records[0]);
        Double price = Double.parseDouble(records[1]);
        Integer quantity = Integer.parseInt(records[2]);
        Double sum = Double.parseDouble(records[3]);
        Date purchaseDate = new Date(Long.parseLong(records[4]));

        PurchaseRecord purchaseRecord = new PurchaseRecord();
        purchaseRecord.setUserId(userId);
        purchaseRecord.setPrice(price);
        purchaseRecord.setQuantity(quantity);
        purchaseRecord.setSum(sum);
        purchaseRecord.setPurchaseTime(purchaseDate);
        purchaseRecord.setProductId(productId);
        return purchaseRecord;
    }
}
