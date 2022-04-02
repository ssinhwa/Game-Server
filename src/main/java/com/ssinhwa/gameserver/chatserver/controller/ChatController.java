package com.ssinhwa.gameserver.chatserver.controller;

import com.ssinhwa.gameserver.chatserver.config.KafkaConstants;
import com.ssinhwa.gameserver.chatserver.dto.MessageDto;
import com.ssinhwa.gameserver.chatserver.repository.ChatMessageHistoryRepository;
import com.ssinhwa.gameserver.chatserver.service.ChatServiceImpl;
import com.ssinhwa.gameserver.chatserver.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


// Publisher 구현

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final ChatServiceImpl chatService;
    private final KafkaTemplate<String, MessageDto> kafkaTemplate;
    private final KafkaProducer kafkaProducer;
    private final ChatMessageHistoryRepository chatMessageHistoryRepository;

    @PostMapping("/publish")
    public void sendMessage(@RequestBody MessageDto messageDto) {
        log.info("ChatController -> sendMessage : " + messageDto.getMessage());
        try {
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, messageDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @MessageMapping("/message")
    public void message(MessageDto message) {
        log.info(message.getMessage());
        chatMessageHistoryRepository.save(message);
        kafkaProducer.send(KafkaConstants.KAFKA_TOPIC, message);
    }

    @GetMapping("/history")
    public List<MessageDto> getMessageHistory() {
        log.info("history 호출");
        return chatMessageHistoryRepository.get();
    }
}
