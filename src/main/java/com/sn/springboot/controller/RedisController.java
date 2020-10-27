package com.sn.springboot.controller;

import com.sn.springboot.redis.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能：
 * 作者：SheHuan
 * 时间：2020/10/27 16:55
 */
@RestController
public class RedisController {
    @Autowired
    PurchaseService purchaseService;

    @GetMapping("/purchase")
    public String purchase() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            boolean result = purchaseService.purchaseByRedis(i, 1, 1);
            System.out.println(i + ":" + (result ? "购买成功" : "购买失败"));
        }
        long end = System.currentTimeMillis();
        return "耗时：" + (end - start) + "毫秒";
    }
}
