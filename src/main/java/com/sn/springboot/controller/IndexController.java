package com.sn.springboot.controller;

import com.sn.springboot.pojo.Book;
import com.sn.springboot.pojo.Car;
import com.sn.springboot.pojo.Cookie;
import com.sn.springboot.pojo.User;
import com.sn.springboot.service.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/main")
public class IndexController {

    @Autowired
    private CookieService cookieService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/index2")
    public String index2(ModelMap modelMap) {
        System.out.println(modelMap.getAttribute("key"));
        int i = 1 / 0;
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

    /**
     * user、book中有同名字段，前台提交数据时，参数名需要添加user.、book.前缀
     * @param user
     * @param book
     */
    @PostMapping("/add")
    public void addInfo(@ModelAttribute("user") User user, @ModelAttribute("book") Book book) {
        System.out.println(user);
        System.out.println(book);
    }
}
