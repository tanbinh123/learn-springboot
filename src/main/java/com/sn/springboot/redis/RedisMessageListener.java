package com.sn.springboot.redis;

import com.sn.springboot.pojo.Phone;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 * 功能：接收消息
 * 作者：SheHuan
 * 时间：2020/9/25 15:03
 */
@Component
public class RedisMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // String body = new String(message.getBody())

        // 消息体
        Phone body = null;
        try {
            body = (Phone) new ObjectInputStream(new ByteArrayInputStream(message.getBody())).readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 渠道名
        String channel = new String(pattern);

        System.out.println(body);
        System.out.println(channel);
    }
}
