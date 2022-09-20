package com.ssinhwa.gameserver.redisserver.service;

import com.ssinhwa.gameserver.redisserver.repository.MapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MapService {
    private final MapRepository mapRepository;

    public String getMapById(String id) {
        return mapRepository.getMapById(id);
    }

    public void addMap(String map, String id) {
        mapRepository.save(map, id);
    }

    public void addMemory(String map, String id) {
        mapRepository.memorySave(map, id);
    }
}
