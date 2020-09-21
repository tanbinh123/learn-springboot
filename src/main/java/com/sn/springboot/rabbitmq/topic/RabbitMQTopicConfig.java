package com.sn.springboot.rabbitmq.topic;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopicConfig {
    public static final String TOPIC_NAME = "my_topic";

    @Value("${rabbitmq.queue4}")
    private String queue4;

    @Value("${rabbitmq.queue5}")
    private String queue5;

    @Bean
    public Queue queue4() {
        return new Queue(queue4);
    }

    @Bean
    public Queue queue5() {
        return new Queue(queue5);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_NAME, true, false);
    }

    @Bean
    public Binding binding4() {
        // #用来模糊匹配队列名称
        // 发送消息时指定的routingKey以queue2开头，则会将消息发送到queue2队列
        return BindingBuilder.bind(queue4()).to(topicExchange()).with("queue4.#");
    }

    @Bean
    public Binding binding5() {
        return BindingBuilder.bind(queue5()).to(topicExchange()).with("#.queue5.#");
    }
}
