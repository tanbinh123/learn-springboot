package com.sn.springboot;

import com.sn.springboot.properties.DataBaseProperties3;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LearnSpringbootApplicationTests {

    @Autowired
    DataBaseProperties3 dataBaseProperties;

    @Test
    void contextLoads() {
        System.out.println(dataBaseProperties.getDriverName());
    }

}
