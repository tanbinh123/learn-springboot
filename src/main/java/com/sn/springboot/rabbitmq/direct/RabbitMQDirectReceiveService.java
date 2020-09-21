package com.sn.springboot.rabbitmq.direct;

import com.sn.springboot.pojo.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQDirectReceiveService {
    // 监听指定消息队列，消费消息
    @RabbitListener(queues = "${rabbitmq.queue1}")
    public void receiveMessage(String message) {
        System.out.println("接收到的消息：" + message);
    }
}
