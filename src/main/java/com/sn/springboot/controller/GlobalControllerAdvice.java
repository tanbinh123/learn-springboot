package com.sn.springboot.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerAdvice {

    /**
     * 全局异常处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, Object> handleException(Exception e) {
        Map<String, Object> result = new HashMap<>();
        result.put("class", e.getClass().getSimpleName());
        result.put("message", e.getMessage());
        return result;
    }

    /**
     * 在控制器执行前，初始化全局数据模型
     */
    @ModelAttribute
    public void prepareData(Model model) {
        model.addAttribute("key", "helloworld666");
    }

    /**
     * 定义控制器参数绑定规则，对请求数据预处理
     */
    @InitBinder("user")
    public void user(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("user.");
    }

    @InitBinder("book")
    public void book(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("book.");
    }
}
