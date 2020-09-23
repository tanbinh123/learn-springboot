package com.sn.springboot.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void send() {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setSubject("邮件主题");
        smm.setText("邮件正文");
        smm.setFrom(from);
        smm.setSentDate(new Date());
        smm.setTo("shehuandev@163.com", "shehuan.dev@gmail.com");
        smm.setBcc("sehuan320@163.com");
        javaMailSender.send(smm);
    }
}
