package com.yangood.socket.boot.handler;

import com.yangood.socket.boot.bean.WsSessionManager;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @ClassName: MySocketHandler
 * @Description: TODO
 * @Author yangweijie
 * @Date 2022/11/15
 * @Version 1.0
 */

@Slf4j
@Component
public class MySocketHandler extends TextWebSocketHandler {

    /**
     * socket 建立成功事件
     *
     * @param session 用户session
     * @throws Exception 失败异常
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Object token = session.getHandshakeHeaders().get("token");
        if (token != null) {
            // 用户连接成功，放入在线用户缓存
            WsSessionManager.add(token.toString(), session);
            session.sendMessage(
                new TextMessage("[socket]"+ token +": 恭喜你成功连接咯!   " +
                    LocalDateTime.now().toString()));
            log.info("[用户连接] "+token+"已连接");
        } else {
            throw new RuntimeException("用户登录已经失效!");
        }
    }

    /**
     * 接收消息事件
     *
     * @param session 用户session
     * @param message 用户发送的信息
     * @throws Exception 失败返回
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 获得客户端传来的消息
        String payload = message.getPayload();
//        Object token = session.getHandshakeHeaders().get("token");
        Object token = session.getAttributes().get("token");
        log.info(token + "说 : " + payload);
        session.sendMessage(
            new TextMessage("[socket]" + token + ":" + payload + " " + LocalDateTime
                .now().toString()));
    }

    /**
     * socket 断开连接时
     *
     * @param session 用户session
     * @param status 连接状态
     * @throws Exception 失败返回
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        Object token = session.getHandshakeHeaders().get("token");
        Object token = session.getAttributes().get("token");
        if ( (token != null) && (CloseStatus.NORMAL.equals(status)) ) {
            // 用户退出，移除缓存
            WsSessionManager.remove(token.toString());
            log.info("[用户断开] "+token+"已断开");
        }
    }

}
