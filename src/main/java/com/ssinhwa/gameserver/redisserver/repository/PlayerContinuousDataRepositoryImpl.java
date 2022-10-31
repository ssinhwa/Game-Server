package com.ssinhwa.gameserver.redisserver.repository;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.ssinhwa.gameserver.redisserver.entity.PlayerContinuousData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Slf4j
public class PlayerContinuousDataRepositoryImpl implements PlayerContinuousDataRepository {
    private static final List<PlayerContinuousData> memoryPlayerContinuousDataRepository = Collections.synchronizedList(new ArrayList<>());

    @Override
    @Transactional
    public void save(PlayerContinuousData data) {
        memoryPlayerContinuousDataRepository.add(data);
    }

    @Override
    @Transactional
    public JsonElement getAll() {
        return new Gson().toJsonTree(memoryPlayerContinuousDataRepository);
    }


    @Transactional
    public void deleteAll() {
        memoryPlayerContinuousDataRepository.clear();
    }
}
