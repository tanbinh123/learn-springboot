package com.sn.springboot.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * 可以和CommandLineRunner公用
 * java -jar xxxxx.jar --key0==value0 --key1==value1 value2 value3
 */
@Component
@Order(10)
public class MyApplicationRunner implements ApplicationRunner {
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
    }
}
