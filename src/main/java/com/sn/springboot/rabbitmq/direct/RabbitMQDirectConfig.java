package com.sn.springboot.rabbitmq.direct;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Direct Exchange 是 RabbitMQ 默认的 Exchange，完全根据 RoutingKey 来路由消息。
 * 设置 Exchange 和 Queue 的 Binding 时需指定 RoutingKey（一般为 Queue Name），
 * 发消息时也指定一样的 RoutingKey，消息就会被路由到对应的Queue
 */
@Configuration
public class RabbitMQDirectConfig {
    public static final String DIRECT_NAME = "my_direct";

    @Value("${rabbitmq.queue1}")
    private String queue1;

    @Bean
    public Queue queue1() {
        return new Queue(queue1, true);
    }


    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_NAME, true, false);
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queue1()).to(directExchange()).with(queue1);
    }
}
