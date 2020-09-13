package com.sn.springboot.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 系统启动只执行一次的任务类
 * Program arguments
 * 打包启动时传参：java -jar xxxxx.jar 参数1 参数2
 */
@Component
// 数字越大优先级越低
@Order(8)
public class MyCommandLineRunner0 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("MyCommandLineRunner0===>" + Arrays.toString(args));
    }
}
