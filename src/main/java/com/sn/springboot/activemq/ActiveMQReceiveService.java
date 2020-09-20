package com.sn.springboot.activemq;

import com.sn.springboot.pojo.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ActiveMQReceiveService {
    /**
     * JmsListener的destination参数是消息队列名称
     *
     * @param message
     */
    @JmsListener(destination = "${spring.jms.template.default-destination}")
    public void receiveMessage(String message) {
        System.out.println("接收到的消息：" + message);
    }

    @JmsListener(destination = "activemq.destination2")
    public void receiveMessage(Message message) {
        System.out.println("接收到的消息：" + message);
    }
}
