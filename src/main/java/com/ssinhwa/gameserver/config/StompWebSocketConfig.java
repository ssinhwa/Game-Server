package com.ssinhwa.gameserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // Message Broker 등록하는 코드
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
        registry.enableSimpleBroker("/topic", "/queue");
        registry.setApplicationDestinationPrefixes("/test");
    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.
                addEndpoint("/example")
                .setAllowedOrigins("*")
                .withSockJS();
    }
}