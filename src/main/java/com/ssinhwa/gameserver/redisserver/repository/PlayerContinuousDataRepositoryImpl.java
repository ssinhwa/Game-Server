package com.ssinhwa.gameserver.redisserver.repository;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.ssinhwa.gameserver.redisserver.entity.PlayerContinuousData;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerContinuousDataRepositoryImpl implements PlayerContinuousDataRepository {
    private static final List<PlayerContinuousData> memoryPlayerContinuousDataRepository = new ArrayList<>();

    @Override
    public void save(PlayerContinuousData data) {
        memoryPlayerContinuousDataRepository.add(data);
    }

    @Override
    public JsonElement getAll() {
        return new Gson().toJsonTree(memoryPlayerContinuousDataRepository);
    }


    public void deleteAll() {
        memoryPlayerContinuousDataRepository.clear();
    }
}
