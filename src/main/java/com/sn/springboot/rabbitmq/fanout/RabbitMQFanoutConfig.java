package com.sn.springboot.rabbitmq.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQFanoutConfig {
    public static final String FANOUT_NAME = "my_fanout";

    @Value("${rabbitmq.queue2}")
    private String queue2;

    @Value("${rabbitmq.queue3}")
    private String queue3;

    @Bean
    public Queue queue2() {
        return new Queue(queue2);
    }

    @Bean
    public Queue queue3() {
        return new Queue(queue3);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_NAME, true, false);
    }

    public Binding binding2() {
        return BindingBuilder.bind(queue2()).to(fanoutExchange());
    }

    public Binding binding3() {
        return BindingBuilder.bind(queue3()).to(fanoutExchange());
    }
}
