package com.yangood.socket.boot.controller;

import com.yangood.socket.boot.bean.Response;
import com.yangood.socket.boot.bean.WsSessionManager;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @ClassName: ServerController
 * @Description: TODO
 * @Author WeiJie Yang
 * @Date 2022/12/03
 * @Version 1.0
 */
@RestController()
@RequestMapping("bootSend")
@Slf4j
public class ServerController {

    @RequestMapping
    public Response sendMessageAll(String msg) throws IOException {
        List<String> tokens = sendMessage(msg);
        Response response = Response.ok().message("发送成功").data("tokens", tokens);
        //String s = null;
        //String s1 = s.toLowerCase();
        return response;
    }


    /**
     * 实现服务器主动推送
     * @return
     */
    public List<String> sendMessage(String message) throws IOException {
        List<String> tokens = new LinkedList<>();
        Map<String, WebSocketSession> sessionMap = WsSessionManager.getAll();
        sessionMap.forEach((token, session) -> {
            try {
                session.sendMessage(new TextMessage(message));
                tokens.add(token);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return tokens;
    }
}
