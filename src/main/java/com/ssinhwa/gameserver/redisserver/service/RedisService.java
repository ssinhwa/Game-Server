package com.ssinhwa.gameserver.redisserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
@Slf4j
public class RedisService {
    private final RedisTemplate<String, String> redisTemplate;

    public void setToken(String token, String username) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(token, username, Duration.ofHours(1)); // 1시간 뒤 삭제
    }

    public String validateToken(String token) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(token);
    }

    public void delToken(String token) {
        Boolean delete = redisTemplate.delete(token);
        log.info("Delete Success : " + delete);
        log.info("Logout : " + token);
    }

}
