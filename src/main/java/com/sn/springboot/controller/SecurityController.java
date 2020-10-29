package com.sn.springboot.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 功能：
 * 作者：SheHuan
 * 时间：2020/10/12 11:44
 */
@Controller
public class SecurityController {
    @Autowired
    Producer producer;

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

    @PostMapping("/doLogin")
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

    @GetMapping("/verifyCode")
    @ResponseBody
    public void verifyCode(HttpServletResponse response, HttpSession session) throws IOException {
        response.setContentType("image/jpeg");
        String text = producer.createText();
        session.setAttribute("verify_code", text);
        BufferedImage image = producer.createImage(text);
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            ImageIO.write(image, "jpg", outputStream);
        }
    }
}
