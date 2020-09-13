package com.sn.springboot.aop;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CoffeeShop implements Shop {
    @Override
    public String sale(String name) {
        if (!StringUtils.isEmpty(name)) {
            System.out.println("开始制作" + name);
            return "制作完成";
        } else {
            throw new RuntimeException("请提供咖啡名称");
        }
    }
}
