package com.sn.springboot.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQTopicReceiveService {
    @RabbitListener(queues = "${rabbitmq.queue4}")
    public void receiveMessage2(String message) {
        System.out.println("接收到的消息：" + message);
    }

    @RabbitListener(queues = "${rabbitmq.queue5}")
    public void receiveMessage3(String message) {
        System.out.println("接收到的消息：" + message);
    }
}
