package com.ssinhwa.gameserver.redisserver.task;

import com.ssinhwa.gameserver.redisserver.config.RedisConstants;
import com.ssinhwa.gameserver.redisserver.repository.MemoryPeriodicDataRepository;
import com.ssinhwa.gameserver.redisserver.repository.MemoryPlayerContinuousDataRepository;
import com.ssinhwa.gameserver.redisserver.service.PacketService;
import com.ssinhwa.gameserver.redisserver.service.RedisDataSubscriber;
import com.ssinhwa.gameserver.redisserver.service.RedisPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class Task {
    private final MemoryPlayerContinuousDataRepository memoryPlayerContinuousDataRepository;
    private final MemoryPeriodicDataRepository memoryPeriodicDataRepository;
    private final RedisPublisher redisPublisher;
    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisDataSubscriber dataSubscriber;
    private final PacketService packetService;

    // 0.1초마다 뿌려줄 것 -> 테스트용은 0.5초

    // @Scheduled(fixedDelay = 60)
    public void scheduledPublish() throws InterruptedException {
        String json = packetService.jsonToString();
        redisMessageListenerContainer.addMessageListener(dataSubscriber, new ChannelTopic(RedisConstants.GAME_TOPIC));
        redisPublisher.publish(new ChannelTopic(RedisConstants.GAME_TOPIC), json);
        memoryPlayerContinuousDataRepository.deleteAll();
        memoryPeriodicDataRepository.deleteAll();
    }
}
