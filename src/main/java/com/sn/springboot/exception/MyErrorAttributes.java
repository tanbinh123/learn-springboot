package com.sn.springboot.exception;


import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 自定义异常数据
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, options);
        Integer status = (Integer) map.get("status");
        switch (status) {
            case 404:
                map.put("message", "访问的路径不存在");
                break;
            case 500:
                map.put("message", "服务器内部错误");
                break;
            default:
                break;
        }
        return map;
    }
}
