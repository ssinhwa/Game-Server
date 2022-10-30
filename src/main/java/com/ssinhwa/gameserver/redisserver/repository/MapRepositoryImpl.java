package com.ssinhwa.gameserver.redisserver.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MapRepositoryImpl implements MapRepository {
    private static final List<String> mapList = new ArrayList<>();
    private final StringRedisTemplate redisTemplate;

    // 서버 시작 시 Redis 에서 Map Data 갖고 와서 Setting 한다.
    @PostConstruct
    public void init() {
        mapList.add(" ");
        for (int i = 0; i <= 33; i++) {
            String key = "Map" + String.valueOf(i);
            String map = redisTemplate.opsForValue().get(key);
            mapList.add(map);
        }
    }

    @Override
    public void save(String map, String id) {
        int idx = Integer.parseInt(id);
        mapList.set(idx, map);
        String key = "Map" + id;
        redisTemplate.opsForValue().set(key, map);
    }

    @Override
    public void memorySave(String map, String id) {
        int idx = Integer.parseInt(id);
        mapList.set(idx, map);
    }

    @Override
    public String getMapById(String id) {
        int idx = Integer.parseInt(id);
        return mapList.get(idx);
    }
}
