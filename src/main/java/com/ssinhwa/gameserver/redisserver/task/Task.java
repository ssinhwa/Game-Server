package com.ssinhwa.gameserver.redisserver.task;

import com.google.gson.Gson;
import com.ssinhwa.gameserver.redisserver.config.RedisConstants;
import com.ssinhwa.gameserver.redisserver.dto.PlayerContinuousData;
import com.ssinhwa.gameserver.redisserver.dto.WPosition;
import com.ssinhwa.gameserver.redisserver.repository.MemoryPlayerContinuousDataRepository;
import com.ssinhwa.gameserver.redisserver.service.RedisDataSubscriber;
import com.ssinhwa.gameserver.redisserver.service.RedisPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class Task {
    private final MemoryPlayerContinuousDataRepository repository;
    private final RedisPublisher redisPublisher;
    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisDataSubscriber dataSubscriber;

    // 0.1초마다 뿌려줄 것
    @Scheduled(fixedDelay = 100)
    public void scheduledPublish() throws InterruptedException {
        WPosition wPosition = new WPosition(3.241f, 5.21f);
        PlayerContinuousData playerContinuousData1 = new PlayerContinuousData(0, 2, wPosition, 90f);
        PlayerContinuousData playerContinuousData2 = new PlayerContinuousData(1, 3, wPosition, 90f);
        repository.save(playerContinuousData1);
        repository.save(playerContinuousData2);
        List<Map<String, PlayerContinuousData>> all = repository.getAll();
        Gson gson = new Gson();
        String s = gson.toJsonTree(all).toString();
        System.out.println("s = " + s);
        redisMessageListenerContainer.addMessageListener(dataSubscriber, new ChannelTopic(RedisConstants.GAME_TOPIC));
        redisPublisher.publish(new ChannelTopic(RedisConstants.GAME_TOPIC), s);
        repository.deleteAll();
        log.info("Fixed Task. Time : " + System.currentTimeMillis());
    }
}
