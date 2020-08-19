package com.sn.springboot.rabbitmq;

import com.sn.springboot.pojo.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQReceiveService {
    @RabbitListener(queues = "${rabbitmq.queue.message}")
    public void receiveMessage(String message) {
        System.out.println("接收到的消息：" + message);
    }

    @RabbitListener(queues = "${rabbitmq.queue.object}")
    public void receiveMessage(Message message) {
        System.out.println("接收到的消息：" + message);
    }
}
