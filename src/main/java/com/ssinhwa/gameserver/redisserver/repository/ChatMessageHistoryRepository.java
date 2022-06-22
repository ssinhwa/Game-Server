package com.ssinhwa.gameserver.redisserver.repository;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.ssinhwa.gameserver.redisserver.dto.MessageDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ChatMessageHistoryRepository {
    private final Cache<String, MessageDto> cache = CacheBuilder
            .newBuilder().maximumSize(20).expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    public void save(MessageDto messageDto) {
        cache.put(messageDto.getRoomId(), messageDto);
    }

    public List<MessageDto> get() {
        return new ArrayList<>(cache.asMap().values());
    }
}
