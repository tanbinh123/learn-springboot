package com.sn.springboot;

import com.sn.springboot.aop.CoffeeShop;
import com.sn.springboot.properties.DataBaseProperties3;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LearnSpringbootApplicationTests {

    @Autowired
    DataBaseProperties3 dataBaseProperties;

    @Autowired
    CoffeeShop coffeeShop;

    @Test
    void doTest() {
//        System.out.println(dataBaseProperties.getDriverName());
//        coffeeShop.sale("拿铁");
    }

}
