package com.sn.springboot.controller;

import com.sn.springboot.pojo.User;
import com.sn.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能：
 * 作者：SheHuan
 * 时间：2020/9/30 11:16
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public User addUser(User user) {
        return userService.addUser(user);
    }

    @GetMapping("/getUserById")
    public User getUserById(Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getUsers")
    public List<User> getUsers(String name, Integer age) {
        return userService.getUsers(name, age);
    }

    @PostMapping("/updateUser")
    public String updateUser(User user) {
        User u = userService.updateUser(user);
        return u == null ? "更新失败" : "更新成功";
    }

    @GetMapping("/deleteUserById")
    public String deleteUserById(Long id) {
        int row = userService.deleteUserById(id);
        return row > 0 ? "删除成功" : "删除失败";
    }
}
