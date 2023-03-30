package com.yangood.socket.jdk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @ClassName: WebSocketConfig
 * @Description: TODO
 * @Author yangweijie
 * @Date 2022/11/15
 * @Version 1.0
 */
@Configuration
@EnableWebSocket
public class JdkWebSocketConfig {

    //@Profile({"dev","prod"})
    @Bean
    public ServerEndpointExporter serverEndpoint() {
        return new ServerEndpointExporter();
    }

}
