package com.ssinhwa.gameserver.redisserver.repository;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.ssinhwa.gameserver.redisserver.dto.PeriodicData;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryPeriodicDataRepository implements PeriodicDataRepository {
    private static List<PeriodicData> memoryPeriodicDataRepository = new ArrayList<>();

    @Override
    public void save(PeriodicData data) {
        memoryPeriodicDataRepository.add(data);
    }

    @Override
    public JsonElement getAll() {
        return new Gson().toJsonTree(memoryPeriodicDataRepository);
    }
}
