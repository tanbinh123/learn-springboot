package com.sn.springboot.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * https://zhuanlan.zhihu.com/p/37198933
 *
 * Default Exchange 是一种特殊的 Direct Exchange。
 * 当你手动创建一个队列时，框架会自动将这个队列绑定到一个名称为空的 Direct Exchange 上，绑定 RoutingKey 与队列名称相同。
 * 有了这个默认的交换机和绑定，使我们只关心队列这一层即可，这个比较适合做一些简单的应用。
 */
@Configuration
public class RabbitMQDefaultConfig {
    @Bean
    public Queue queue() {
        return new Queue("my_queue", true);
    }
}
