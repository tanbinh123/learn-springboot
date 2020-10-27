package com.sn.springboot.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 可以和CommandLineRunner公用
 * java -jar xxxxx.jar --key0==value0 --key1==value1 value2 value3
 */
@Component
@Order(10)
public class MyApplicationRunner implements ApplicationRunner {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String[] sourceArgs = args.getSourceArgs();
        System.out.println("sourceArgs===>" + Arrays.toString(sourceArgs));

        List<String> nonOptionArgs = args.getNonOptionArgs();
        System.out.println("nonOptionArgs===>" + nonOptionArgs);

        Set<String> optionNames = args.getOptionNames();
        System.out.println("==================================");
        for (String optionName : optionNames) {
            System.out.println(optionName + ":" + args.getOptionValues(optionName));
        }

        Map<String, String> hash = new HashMap<>();
        hash.put("stock", "10000");
        hash.put("price", "2299");
        // 存入散列数据类型
        stringRedisTemplate.opsForHash().putAll("product_1", hash);
    }
}
