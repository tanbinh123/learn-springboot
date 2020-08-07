package com.sn.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:jdbc.properties"}, ignoreResourceNotFound = true)
public class LearnSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnSpringbootApplication.class, args);
    }

}
