package com.sn.springboot.rabbitmq.topic;

import com.sn.springboot.rabbitmq.RabbitMQSendService;
import com.sn.springboot.rabbitmq.fanout.RabbitMQFanoutConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQTopicSendService implements RabbitTemplate.ConfirmCallback, RabbitMQSendService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(String message) {
        // 设置发送消息是否已被消费的回调
        rabbitTemplate.setConfirmCallback(this);
        System.out.println("发送的消息：" + message);
        // 根据routingKey匹配和TopicExchange绑定的消息队列，然后将消息发送到匹配到的队列
        rabbitTemplate.convertAndSend(RabbitMQTopicConfig.TOPIC_NAME, "queue4.hello.world", message);
        rabbitTemplate.convertAndSend(RabbitMQTopicConfig.TOPIC_NAME, "queue5.hello.world", message);
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
