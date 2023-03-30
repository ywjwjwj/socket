package com.yangood.socket.boot.config;

import com.yangood.socket.boot.handler.MySocketHandler;
import com.yangood.socket.boot.interptor.MySocketInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @ClassName: WebSocketConfig
 * @Description: TODO
 * @Author yangweijie
 * @Date 2022/11/15
 * @Version 1.0
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Resource
    MySocketHandler mySocketHandler;

    @Resource
    MySocketInterceptor mySocketInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
            .addHandler(mySocketHandler, "boot")
            .addInterceptors(mySocketInterceptor)
            .setAllowedOrigins("*");
    }
}
