package com.sn.springboot.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQFanoutReceiveService {
    @RabbitListener(queues = "${rabbitmq.queue2}")
    public void receiveMessage2(String message) {
        System.out.println("接收到的消息：" + message);
    }

    @RabbitListener(queues = "${rabbitmq.queue3}")
    public void receiveMessage3(String message) {
        System.out.println("接收到的消息：" + message);
    }
}
