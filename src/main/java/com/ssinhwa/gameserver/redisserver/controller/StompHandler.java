package com.ssinhwa.gameserver.redisserver.controller;

import com.ssinhwa.gameserver.auth.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
@Slf4j
public class StompHandler implements ChannelInterceptor {

    private final TokenProvider tokenProvider;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        log.info("StompHandler Message 실행 : " + message.toString());
        if (StompCommand.CONNECT == accessor.getCommand()) {
            if (!tokenProvider.validateToken(accessor.getFirstNativeHeader("token"))) {
                throw new RuntimeException("토큰 값이 없습니다.");
            }
        }
        return message;
    }
}