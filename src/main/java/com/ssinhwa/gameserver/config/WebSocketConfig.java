package com.ssinhwa.gameserver.config;

import com.ssinhwa.gameserver.handler.MyHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/myHandler");
    }

    public WebSocketHandler myHandler() {
        return new MyHandler();
    }
}

// https://velog.io/@hanblueblue/번역-Spring-4-Spring-WebSocket