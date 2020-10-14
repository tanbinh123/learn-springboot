package com.sn.springboot.websocket;

import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能：
 * 作者：SheHuan
 * 时间：2020/10/14 15:57
 */

/**
 * 定义WebSocket服务器端点
 */
@ServerEndpoint("/ws")
@Service
public class WebSocketService {
    // 连接数量
    private AtomicInteger onlineCount = new AtomicInteger();
    private static CopyOnWriteArraySet<WebSocketService> webSocketSet = new CopyOnWriteArraySet<>();
    // 与某个客户端连接的会话，通过它给客户端发送消息
    private Session session;

    /**
     * 建立连接
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        System.out.println("有连接加入，当前连接数：" + onlineCount.incrementAndGet());
        sendMessage("服务端：连接成功");
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        System.out.println("有连接关闭，当前连接数：" + onlineCount.decrementAndGet());
    }

    /**
     * 收到客户端消息
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("客户端发来的消息：" + message);
        // 群发消息
        for (WebSocketService item : webSocketSet) {
            System.out.println(item.getSession().getUserPrincipal().getName());
            item.sendMessage("服务端：收到");
        }

    }

    /**
     * 发生错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误：" + error.getMessage());
    }

    private void sendMessage(String msg) {
        try {
            session.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Session getSession() {
        return session;
    }
}
