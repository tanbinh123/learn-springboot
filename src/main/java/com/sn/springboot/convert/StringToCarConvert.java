package com.sn.springboot.convert;

import com.sn.springboot.pojo.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCarConvert implements Converter<String, Car> {
    @Autowired
    Car car;
    @Override
    public Car convert(String s) {
        String[] strs = s.split("-");
        String brand = strs[0];
        double price = Double.parseDouble(strs[1]);
        car.setBrand(brand);
        car.setPrice(price);
        return car;
    }
}
