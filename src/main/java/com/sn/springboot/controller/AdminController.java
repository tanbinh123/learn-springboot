package com.sn.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能：
 * 作者：SheHuan
 * 时间：2020/10/9 14:49
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }

    @GetMapping("/logout_result")
    public String logout_result(){
        return "logout_result";
    }
}
