package com.sn.springboot.websocket.stomp;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * 功能：
 * 作者：SheHuan
 * 时间：2020/10/15 10:33
 */
@EnableWebSocketMessageBroker
@Configuration
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // withSockJS()表示支持SockJS前端框架
        // 增加单聊服务端点
        registry.addEndpoint("/singleChat").withSockJS();
        // 增加群聊服务端点
        registry.addEndpoint("/groupChat").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 客户端订阅路径前缀，
        registry.enableSimpleBroker("/single", "/group");
        // 服务端点请求前缀，客户端请求MessageMapping配置的路径发送消息时需要添加该前缀
        registry.setApplicationDestinationPrefixes("/request");
        // 设置点对点订阅时的前缀，默认就是user
        registry.setUserDestinationPrefix("/user");
    }
}
