package com.sn.springboot.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Car {
    @Value("#{T(System).currentTimeMillis()}")
    public long initTime;


}
