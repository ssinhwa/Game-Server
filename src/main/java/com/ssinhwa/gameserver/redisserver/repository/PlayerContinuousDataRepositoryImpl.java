package com.ssinhwa.gameserver.redisserver.repository;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.ssinhwa.gameserver.redisserver.entity.PlayerContinuousData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PlayerContinuousDataRepositoryImpl implements PlayerContinuousDataRepository {
    private static final List<PlayerContinuousData> memoryPlayerContinuousDataRepository = new ArrayList<>();
    private final Gson gson;

    @Override
    public synchronized void save(PlayerContinuousData data) {
        memoryPlayerContinuousDataRepository.add(data);
    }

    @Override
    public synchronized JsonElement getAll() {
        return gson.toJsonTree(memoryPlayerContinuousDataRepository);
    }

    @Override
    public synchronized void deleteAll() {
        memoryPlayerContinuousDataRepository.clear();
    }
}
