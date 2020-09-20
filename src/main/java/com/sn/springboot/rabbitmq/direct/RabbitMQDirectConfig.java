package com.sn.springboot.rabbitmq.direct;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQDirectConfig {
    @Value("${rabbitmq.queue1}")
    private String queue1;

    @Bean
    public Queue queue1() {
        return new Queue(queue1, true);
    }

//    DirectExchange

//    BindingBuilder
}
