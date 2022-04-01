package com.ssinhwa.gameserver.chatserver.service;

import com.ssinhwa.gameserver.chatserver.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    public void send(String topic, MessageDto messageDto) {
        log.info("topic : " + topic);
        log.info("send Message : " + messageDto.getMessage());
        kafkaTemplate.send(topic, messageDto);
    }
}
