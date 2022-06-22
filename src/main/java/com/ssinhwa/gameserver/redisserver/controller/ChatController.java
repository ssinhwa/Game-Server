package com.ssinhwa.gameserver.redisserver.controller;

import com.ssinhwa.gameserver.auth.jwt.TokenProvider;
import com.ssinhwa.gameserver.redisserver.config.RedisConstants;
import com.ssinhwa.gameserver.redisserver.dto.MessageDto;
import com.ssinhwa.gameserver.redisserver.repository.ChatMessageHistoryRepository;
import com.ssinhwa.gameserver.redisserver.service.ChatServiceImpl;
import com.ssinhwa.gameserver.redisserver.service.RedisPublisher;
import com.ssinhwa.gameserver.redisserver.service.RedisSubscriber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Publisher 구현

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final ChatServiceImpl chatService;
    //    private final KafkaTemplate<String, MessageDto> kafkaTemplate;
//    private final KafkaProducer kafkaProducer;
    private final ChatMessageHistoryRepository chatMessageHistoryRepository;
    private final RedisPublisher redisPublisher;
    private final RedisSubscriber redisSubscriber;
    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final TokenProvider tokenProvider;

    @PostMapping("/publish")
    public void sendMessage(@RequestBody MessageDto messageDto) {
        log.info("ChatController -> sendMessage : " + messageDto.getMessage());
//        try {
//            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, messageDto);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        redisPublisher.publish(new ChannelTopic(RedisConstants.REDIS_TOPIC), messageDto);
    }

    @MessageMapping("/chat/message")
    public void message(@RequestBody MessageDto message) {
        String username = message.getWriter();
        log.info("WebSocket Username : " + username);
        log.info(message.getMessage());
        chatMessageHistoryRepository.save(message);
        redisMessageListenerContainer.addMessageListener(redisSubscriber, new ChannelTopic(RedisConstants.REDIS_TOPIC));
        redisPublisher.publish(new ChannelTopic(RedisConstants.REDIS_TOPIC), message);
//        kafkaProducer.send(KafkaConstants.KAFKA_TOPIC, message);
    }

    @GetMapping("/history")
    @ResponseBody
    public List<MessageDto> getMessageHistory() {
        log.info("history 호출");
        return chatMessageHistoryRepository.get();
    }

}
