package com.sn.springboot.controller;

import com.sn.springboot.pojo.Car;
import com.sn.springboot.pojo.Cookie;
import com.sn.springboot.service.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private CookieService cookieService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @ResponseBody
    @RequestMapping("/getUserById")
    public Cookie getUserById(Integer id) {
        return cookieService.getCookieById(id);
    }

    @GetMapping("/convert")
    @ResponseBody
    public Car convert(Car car) {
        return car;
    }

    @GetMapping("/carList")
    @ResponseBody
    public List<Car> carList(List<Car> carList) {
        return carList;
    }

    @GetMapping("/array")
    @ResponseBody
    public String[] array(String[] array) {
        return array;
    }
}
