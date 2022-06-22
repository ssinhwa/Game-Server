package com.ssinhwa.gameserver.redisserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssinhwa.gameserver.redisserver.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class RedisSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, MessageDto> redisTemplate;
    private final SimpMessageSendingOperations template;


    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            // 발행된 데이터를 Deserialize
            log.info("Redis Subscriber 호출");
            String publishMessage = redisTemplate
                    .getStringSerializer().deserialize(message.getBody());
            MessageDto messageDto = objectMapper.readValue(publishMessage, MessageDto.class);
            log.info("Redis Subscribe Message : " + messageDto.getMessage());
            template.convertAndSend("/sub/chat/room/" + messageDto.getRoomId(), messageDto.getMessage());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }

    }
}
