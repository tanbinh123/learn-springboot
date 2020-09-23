package com.sn.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 * 作者：SheHuan
 * 时间：2020/9/23 14:41
 */
@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void testString() {
        redisTemplate.opsForValue().set("key1", "value1");
        System.out.println((String) redisTemplate.opsForValue().get("key1"));

        // 默认情况下，RedisTemplate使用的JDK序列化器，不能使用运算函数
        stringRedisTemplate.opsForValue().set("key2", "1");
        stringRedisTemplate.opsForValue().increment("key2", 1);
        System.out.println(stringRedisTemplate.opsForValue().get("key2"));
    }

    public void testHash() {
        Map<String, String> hash = new HashMap<>();
        hash.put("key1", "value1");
        hash.put("key2", "value2");
        // 存入散列数据类型
        stringRedisTemplate.opsForHash().putAll("hash", hash);
        // 添加单个键值对
        stringRedisTemplate.opsForHash().put("hash", "key3", "value3");
        // 绑定key，实现数据的连续操作
        BoundHashOperations hashOps = stringRedisTemplate.boundHashOps("hash");
        hashOps.delete("key3");
        hashOps.put("key4", "value4");
        hashOps.keys().forEach(key -> {
            System.out.println(key + ":" + hashOps.get(key));
        });
    }

    public void testList() {
        stringRedisTemplate.opsForList().leftPushAll("list1", "v1", "v2", "v3", "v4");
        stringRedisTemplate.opsForList().rightPushAll("list2", "v3", "v4", "v5", "v6");
        BoundListOperations<String, String> list1Ops = stringRedisTemplate.boundListOps("list1");
        System.out.println(list1Ops.rightPop());
        System.out.println(list1Ops.index(0));
        list1Ops.rightPush("v0");
        System.out.println(list1Ops.size());
        List<String> elements = list1Ops.range(1, list1Ops.size() - 1);
        for (String element : elements) {
            System.out.println(element);
        }
    }

    public void testSet() {

    }

    public void testZSet() {

    }
}
