package com.sn.springboot.websocket;

import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
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
    private final AtomicInteger onlineCount = new AtomicInteger();
    private static final ConcurrentHashMap<String, WebSocketService> webSocketMap = new ConcurrentHashMap<>();
    // 与某个客户端连接的会话，通过它给客户端发送消息
    private Session session;

    /**
     * 建立连接
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        String uName = session.getUserPrincipal().getName();
        webSocketMap.put(uName, this);
        System.out.println("有连接加入，当前连接数：" + onlineCount.incrementAndGet());
        sendMessage(uName, "服务端：连接成功");
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose(Session session) {
        String uName = session.getUserPrincipal().getName();
        webSocketMap.remove(uName);
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
        String uName = session.getUserPrincipal().getName();
        // 单发消息
        sendMessage(uName, "服务端：收到");
        // 群发消息
//        sendMessageToAll("服务端：主动推送的消息");
    }

    /**
     * 发生错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误：" + error.getMessage());
    }

    public static void sendMessage(String uName, String msg) {
        try {
            webSocketMap.get(uName).getSession().getBasicRemote().sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessageToAll(String msg) {
        webSocketMap.values().forEach(i -> {
            try {
                i.getSession().getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Session getSession() {
        return session;
    }
}
