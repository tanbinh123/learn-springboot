package com.sn.springboot.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private TemplateEngine templateEngine;

    public void send() {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setSubject("邮件主题");
        smm.setText("邮件正文\nhello spring boot");
        // 发送人
        smm.setFrom(from);
        // 发送日期
        smm.setSentDate(new Date());
        // 要发给的人
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
            // 设置邮件内容
            mmh.setText("<html><body>邮件正文\n<img src='cid:img1'/>\n<h1>hello spring boot</h1></body></html>", true);
            // 配置图片资源
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

    public void send3() {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
            mmh.setSubject("国庆放假通知");

            // 配置Thymeleaf邮件模板
            Context context = new Context();
            // 准备邮件模板参数
            Map<String, Object> params = new HashMap<>();
            params.put("festival", "国庆节");
            params.put("date", "2020年10月1日至2020年10月8日");
            context.setVariables(params);
            String process = templateEngine.process("notice.html", context);
            mmh.setText(process, true);

            mmh.setFrom(from);
            mmh.setSentDate(new Date());
            mmh.setTo("shehuandev@163.com");
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void send4() {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
            mmh.setSubject("国庆放假通知");

            // 配置FreeMarker邮件模板
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
            configuration.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "templates");
            Template template = configuration.getTemplate("notice.ftlh");
            // 准备邮件模板参数
            Map<String, Object> params = new HashMap<>();
            params.put("festival", "国庆节");
            params.put("date", "2020年10月1日至2020年10月8日");
            StringWriter out = new StringWriter();
            template.process(params, out);
            mmh.setText(out.toString(), true);

            mmh.setFrom(from);
            mmh.setSentDate(new Date());
            mmh.setTo("shehuandev@163.com");
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
