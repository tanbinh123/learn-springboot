package com.sn.springboot.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
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
        smm.setText("邮件正文\n<h1>hello spring boot</h1>");
        smm.setFrom(from);
        // 发送日期
        smm.setSentDate(new Date());
        smm.setTo("shehuandev@163.com");
        // 密送
        smm.setBcc("shehuan320@163.com");
        // 抄送
        smm.setCc("shehuan.dev@gmail.com");
        javaMailSender.send(smm);
    }

    public void send2() {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
            mmh.setSubject("邮件主题");
            mmh.setText("<html><body>邮件正文\n<img src='cid:img1'/>\n<h1>hello spring boot</h1></body></html>", true);
            mmh.addInline("img1", new File("src/main/resources/static/favicon.ico"));
            mmh.setFrom(from);
            // 发送日期
            mmh.setSentDate(new Date());
            mmh.setTo("shehuandev@163.com");
            // 添加附件
            mmh.addAttachment("favicon.ico", new File("src/main/resources/static/favicon.ico"));
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
