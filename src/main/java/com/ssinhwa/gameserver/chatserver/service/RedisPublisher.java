package com.ssinhwa.gameserver.chatserver.service;

import com.ssinhwa.gameserver.chatserver.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class RedisPublisher {

    private final RedisTemplate<String, MessageDto> redisTemplate;

    public void publish(ChannelTopic topic, MessageDto message) {
        log.info("Topic : " + topic.getTopic() + " Message : " + message.getMessage());
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
