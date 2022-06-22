package com.ssinhwa.gameserver.redisserver.service;

import com.ssinhwa.gameserver.redisserver.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class RedisPublisher {

    private final RedisTemplate<String, Object> redisTemplate;

    public void publish(ChannelTopic topic, MessageDto message) {
        log.info("Topic : " + topic.getTopic() + " Message : " + message.getMessage());
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }


    public void publish(ChannelTopic topic, String data) {
        log.info("Topic : " + topic.getTopic());
        log.info("Data : " + data);
        redisTemplate.convertAndSend(topic.getTopic(), data);
    }
}
