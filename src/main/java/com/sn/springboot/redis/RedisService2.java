package com.sn.springboot.redis;

import com.sn.springboot.pojo.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 功能：
 * 作者：SheHuan
 * 时间：2020/9/25 11:30
 */
@Service
public class RedisService2 {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 事务
     */
    public void testTransaction() {
        stringRedisTemplate.opsForValue().set("book1", "spring boot");
        stringRedisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                // 设置要监控的key
                operations.watch("book1");
                // 开启事务，在执行exec命令前，全部都只是进入队列
                operations.multi();

//                operations.opsForValue().increment("book1", 1);

                operations.opsForValue().set("book2", "spring cloud");
                String book2 = (String) operations.opsForValue().get("book2");
                // 命令在队列里，还没执行，所以book2的值为null
                System.out.println("book2===" + book2);
                // 执行exec命令前先判断book1是否在监控后被修改过，如果是则取消事务，否则执行事务
                // 和数据库事务不同的是，某个命令出错，不影响其后边的命令
                return operations.exec();
            }
        });
    }

    /**
     * 流水线
     * 需要执行大量命令时可以使用流水线来提高性能，同样数量的命令使用流水线可以提升大约10倍的速度
     */
    public void testPipline() {
        long start = System.currentTimeMillis();
        stringRedisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                for (int i = 0; i < 100000; i++) {
                    // 所有的命令会先进入队列，之后再执行
                    operations.opsForValue().set("pipline_" + i, "value_" + i);
                }
                return null;
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "毫秒");
    }

    /**
     * 发送消息
     */
    public void testSend() {
        redisTemplate.convertAndSend("channel_1", new Phone("mi10", 3999));
    }
}
