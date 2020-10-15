package com.sn.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * 功能：
 * 作者：SheHuan
 * 时间：2020/10/15 10:39
 */
@Controller
@RequestMapping("/ws")
public class WebSocketController {

    // 消息发送模板
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/websocket")
    public String websocket() {
        return "websocket/websocket";
    }

    // 群发页面
    @GetMapping("/groupSend")
    public String groupSend() {
        return "websocket/group_send";
    }

    // 接收群发消息页面
    @GetMapping("/groupReceive")
    public String groupReceive() {
        return "websocket/group_receive";
    }

    // 发送给指定用户页面
    @GetMapping("/singleSend")
    public String singleSend() {
        return "websocket/single_send";
    }

    // 接收消息页面
    @GetMapping("/singleReceive")
    public String singleReceive() {
        return "websocket/single_receive";
    }

    // 消息发送的请求路径
    @MessageMapping("/groupSendMsg")
    // 将消息发送给监听该路径的客户端
    @SendTo("/group/chat")
    public String sendMsg(String msg) {
        return msg;
    }

    // 将消息发送给指定用户
    @MessageMapping("/singleSendMsg")
    public void sendMsgToUser(Principal principal, String body) {
        // 如果使用了Spring Security，则可以获得当前用户名
        String srcName = principal.getName();
        String[] args = body.split(",");
        // 消息要发给的目标用户
        String destName = args[0];
        String msg = "【" + srcName + "】发的消息：" + args[1];
        simpMessagingTemplate.convertAndSendToUser(destName, "/single/chat", msg);
    }
}
