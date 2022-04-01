package com.ssinhwa.gameserver.chatserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssinhwa.gameserver.chatserver.config.KafkaConstants;
import com.ssinhwa.gameserver.chatserver.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final SimpMessagingTemplate template;

    @KafkaListener(topics = KafkaConstants.KAFKA_TOPIC, groupId = KafkaConstants.GROUP_ID)
    public void consume(MessageDto message) throws IOException {
        log.info("Consumed Message : " + message.getMessage());
        HashMap<String, String> msg = new HashMap<>();
        msg.put("roomId", message.getRoomId());
        msg.put("message", message.getMessage());
        msg.put("writer", message.getWriter());

        ObjectMapper mapper = new ObjectMapper();
        template.convertAndSend("/topic/tt", mapper.writeValueAsString(msg));
    }
}
