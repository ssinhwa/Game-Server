package com.ssinhwa.gameserver.redisserver.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisRepositoryTest {

    @Autowired
    private StringRedisTemplate template;

    @Test
    public void template() {
        template.opsForValue().set("TEST", "TEST");
    }
}