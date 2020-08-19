package com.sn.springboot.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.message}")
    private String messageQueueName;

    @Value("${rabbitmq.queue.object}")
    private String objectQueueName;

    @Bean
    public Queue createMessageQueue() {
        return new Queue(messageQueueName, true);
    }

    @Bean
    public Queue createObjectQueue() {
        return new Queue(objectQueueName, true);
    }
}
