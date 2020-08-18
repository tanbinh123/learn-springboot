package com.sn.springboot.activemq;

import com.sn.springboot.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActiveMqSendService {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String message) {
        System.out.println("发送的消息：" + message);
        jmsTemplate.convertAndSend(message);
    }

    public void sendMessage(Message message) {
        System.out.println("发送的消息：" + message);
        jmsTemplate.convertAndSend("activemq.destination2", message);
    }
}
