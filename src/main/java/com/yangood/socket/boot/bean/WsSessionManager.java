package com.yangood.socket.boot.bean;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

/**
 * @ClassName: WsSessionManager
 * @Description: TODO
 * @Author yangweijie
 * @Date 2022/11/15
 * @Version 1.0
 */

@Slf4j
public class WsSessionManager {

    /**
     * ConcurrentHashMap 作为 session池
     */
    private static ConcurrentHashMap<String, WebSocketSession> SESSION_POOL = new ConcurrentHashMap<>();

    /**
     * 获取用户session
     * @param token 用户token
     * @return 用户session
     */
    public static WebSocketSession get(String token) {
        return SESSION_POOL.get(token);
    }

    /**
     * 获取用户session
     * @return 用户session
     */
    public static Map<String, WebSocketSession> getAll() {
        return SESSION_POOL;
    }

    /**
     * 存入session
     * @param token 用户token
     * @param session 用户session
     */
    public static void add(String token, WebSocketSession session) {
        SESSION_POOL.put(token, session);
    }

    /**
     * 删除session, 会返回删除的session
     * @param token 用户token
     * @return 用户session
     */
    public static WebSocketSession remove(String token) {
        return SESSION_POOL.remove(token);
    }

    /**
     * 删除session并关闭连接
     * @param token 用户token
     */
    public static void removeAndClose(String token) {
        WebSocketSession session = SESSION_POOL.remove(token);
        if (session != null) {
            try {
                session.close();
            } catch (IOException e) {
                new RuntimeException("关闭session连接异常");
            }
        }
    }
}
