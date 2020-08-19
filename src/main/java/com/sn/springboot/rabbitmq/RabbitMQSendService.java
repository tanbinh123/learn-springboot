package com.sn.springboot.rabbitmq;

import com.sn.springboot.pojo.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSendService implements RabbitTemplate.ConfirmCallback {
    @Value("${rabbitmq.queue.message}")
    private String messageRoutingKey;

    @Value("${rabbitmq.queue.object}")
    private String objectRoutingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        // 设置发送消息是否已被消费的回调
        rabbitTemplate.setConfirmCallback(this);
        System.out.println("发送的消息：" + message);
        rabbitTemplate.convertAndSend(messageRoutingKey, message);
    }

    public void sendMessage(Message message) {
        // 设置发送消息是否已被消费的回调
        rabbitTemplate.setConfirmCallback(this);
        System.out.println("发送的消息：" + message);
        rabbitTemplate.convertAndSend(objectRoutingKey, message);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("消息已被消费");
        } else {
            System.out.println("消息未被消费：" + cause);
        }
    }
}
