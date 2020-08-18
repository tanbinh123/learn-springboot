package com.sn.springboot;

import com.sn.springboot.activemq.ActiveMqSendService;
import com.sn.springboot.aop.CoffeeShop;
import com.sn.springboot.pojo.Message;
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

    @Autowired
    ActiveMqSendService activeMqSendService;

    @Test
    void doTest() {
//        System.out.println(dataBaseProperties.getDriverName());
//        coffeeShop.sale("拿铁");

        Message message = new Message("2020-8-11", "雷军演讲");
        activeMqSendService.sendMessage(message);
    }

}
