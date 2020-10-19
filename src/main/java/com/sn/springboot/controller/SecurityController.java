package com.sn.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能：
 * 作者：SheHuan
 * 时间：2020/10/12 11:44
 */
@Controller
public class SecurityController {
    @GetMapping("/admin/hello")
    @ResponseBody
    public String admin() {
        return "admin hello";
    }

    @GetMapping("/user/hello")
    @ResponseBody
    public String user() {
        return "user hello";
    }

    @GetMapping("/supporter/hello")
    @ResponseBody
    public String visitor() {
        return "supporter hello";
    }

    @GetMapping("/common/hello")
    @ResponseBody
    public String common() {
        return "common hello";
    }

    @GetMapping("/main/hello")
    @ResponseBody
    public String main() {
        return "main hello";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/doLogin")
    public void doLogin() {

    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    @GetMapping("/logout_result")
    public String logout_result() {
        return "logout_result";
    }
}
