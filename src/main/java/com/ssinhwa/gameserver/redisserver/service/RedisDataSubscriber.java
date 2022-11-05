package com.ssinhwa.gameserver.redisserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class RedisDataSubscriber implements MessageListener {

    private final SimpMessageSendingOperations template;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 발행된 데이터를 Deserialize
        template.convertAndSend("/sub/game", message.getBody());
    }
}
