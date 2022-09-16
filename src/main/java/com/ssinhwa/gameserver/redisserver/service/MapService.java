package com.ssinhwa.gameserver.redisserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MapService {
    private final StringRedisTemplate redisTemplate;

    public String getMapById(String id) {
        return redisTemplate.opsForValue().get(id);
    }

    public void addMap(String map, String id) {
        redisTemplate.opsForValue().set(id, map);
    }
}
