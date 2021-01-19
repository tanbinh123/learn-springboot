package com.sn.springboot.rabbitmq.topic;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Topic Exchange 和 Direct Exchange 类似，也需要通过 RoutingKey 来路由消息，
 * 区别在于Direct Exchange 对 RoutingKey 是精确匹配，
 * 而 Topic Exchange 支持模糊匹配。分别支持*和#通配符，*表示匹配一个单词，#则表示匹配没有或者多个单词
 */
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
        // *表示匹配一个单词，#则表示匹配0个或者多个单词，单词之间用.分隔
        // 如果修改了routingKey的匹配规则，不能正确的按照匹配规则将消息路由到指定的队列，则需要去RabbitMQ后台删除对应的Exchange
        // 发送消息时指定的routingKey以queue4开头，则会将消息发送到queue4队列
        return BindingBuilder.bind(queue4()).to(topicExchange()).with("queue4.*");
    }

    @Bean
    public Binding binding5() {
        return BindingBuilder.bind(queue5()).to(topicExchange()).with("queue5.#");
    }
}
