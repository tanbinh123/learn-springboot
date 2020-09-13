package com.sn.springboot.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConvert implements Converter<String, Date> {
    private SimpleDateFormat sdf = new SimpleDateFormat();

    @Override
    public Date convert(String s) {
        if (!StringUtils.isEmpty(s)){
            try {
                return sdf.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
