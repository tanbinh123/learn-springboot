package com.sn.springboot.redis;

import com.sn.springboot.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 功能：操作redis常用数据类型
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
        stringRedisTemplate.opsForValue().set("key1", "1");
        stringRedisTemplate.opsForValue().increment("key1", 1);
        System.out.println(stringRedisTemplate.opsForValue().get("key1"));
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
        // 从左边插入，最终从左到右的顺序为v4、v3、v2、v1
        stringRedisTemplate.opsForList().leftPushAll("list1", "v1", "v2", "v3", "v4");
        // 从右边插入
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
        stringRedisTemplate.opsForSet().add("set1", "v1", "v2", "v3", "v4");
        stringRedisTemplate.opsForSet().add("set2", "v3", "v4", "v5", "v6");

        BoundSetOperations<String, String> set1Ops = stringRedisTemplate.boundSetOps("set1");
        // 添加
        set1Ops.add("v5", "v6");
        // 删除
        set1Ops.remove("v1", "v6");
        // 求交集
        Set<String> inter = set1Ops.intersect("set2");
        // 求交集，并用新集合inter1保存
        set1Ops.intersectAndStore("set2", "inter1");
        // 求交集，并用新集合inter2保存
        set1Ops.intersectAndStore(new HashSet<String>() {
            {
                add("v0");
                add("v1");
                add("v2");
            }
        }, "inter2");
        // 求差集
        Set<String> diff = set1Ops.diff("set2");
        // 求并集
        Set<String> union = set1Ops.union("set2");
    }

    public void testZSet() {
        Set<TypedTuple<String>> zSet = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            // 分数
            double score = i * 0.1;
            TypedTuple<String> typedTuple = new DefaultTypedTuple<String>("value" + i, score);
            zSet.add(typedTuple);
        }
        stringRedisTemplate.opsForZSet().add("zSet", zSet);
        // 绑定zSet有序集合操作
        BoundZSetOperations<String, String> zSetOps = stringRedisTemplate.boundZSetOps("zSet");
        // 添加单个
        zSetOps.add("value10", 0.66);
        // 索引范围内，按分数排序（默认都是升序）
        Set<String> scoreSet = zSetOps.range(1, 5);
        // 分数范围内，按分数排序
        Set<String> scoreRange = zSetOps.rangeByScore(0.3, 0.8);

        // 定义range
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        // 大于value5
        range.gt("value5");
        // 小于等于value8
        range.lte("value8");
        Set<String> lexRange = zSetOps.rangeByLex(range);

        // 求分数
        Double score = zSetOps.score("value2");

        // 索引范围内，按分数排序
        Set<TypedTuple<String>> rangeSetTuple = zSetOps.rangeWithScores(1, 5);
        // 分数范围内，按分数排序
        Set<TypedTuple<String>> scoreSetTuple = zSetOps.rangeByScoreWithScores(0.3, 0.8);

        // 降序
        Set<String> scoreSet2 = zSetOps.reverseRange(1, 5);

    }

    public void testObject() {
        // 默认情况下，RedisTemplate使用的JDK序列化器，不能使用运算函数
        redisTemplate.opsForValue().set("key2", "value2");
        System.out.println((String) redisTemplate.opsForValue().get("key2"));

        Message message = new Message("2020-10-01", "放假通知");
        redisTemplate.opsForValue().set("message", message);
        Message msg = (Message) redisTemplate.opsForValue().get("message");
        System.out.println(msg);
    }
}
