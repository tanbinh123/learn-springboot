package com.sn.springboot.controller;

import com.sn.springboot.activemq.ActiveMQSendService;
import com.sn.springboot.pojo.*;
import com.sn.springboot.rabbitmq.RabbitMQSendService;
import com.sn.springboot.service.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/main")
public class IndexController {

    @Autowired
    private CookieService cookieService;

    @Autowired
    private ActiveMQSendService activeMqSendService;

    @Autowired
    private RabbitMQSendService rabbitMQSendService;

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
     *
     * @param user
     * @param book
     */
    @PostMapping("/add")
    public void addInfo(@ModelAttribute("user") User user, @ModelAttribute("book") Book book) {
        System.out.println(user);
        System.out.println(book);
    }

    @PostMapping("/test/{id}")
    public void test(@PathVariable("id") String id) {
        System.out.println(id);
    }

    /**
     * 通过body提交json格式的数据，headers需设置content-type:application/json
     *
     * @param book
     */
    @PostMapping("/test2")
    public void test2(@RequestBody Book book) {
        System.out.println(book);
    }

    /**
     * consumes 限定接收的媒体类型
     * produces 限定返回的媒体类型
     *
     * @return
     */
    @GetMapping(value = "/plain_value",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String getPlanValue() {
        return "xyz";
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    @GetMapping("/book/{name}")
    public Book book(@PathVariable("name") String name) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(30);
        return book;
    }

    @ResponseBody
    @GetMapping("/message")
    public String message(String title) {
//        activeMqSendService.sendMessage(title);
        rabbitMQSendService.sendMessage(title);
        return "message:" + title;
    }

    @ResponseBody
    @GetMapping("/message2")
    public String message2(Message message) {
//        activeMqSendService.sendMessage(message);
        rabbitMQSendService.sendMessage(message);
        return "message:" + message;
    }
}
