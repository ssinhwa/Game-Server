package com.ssinhwa.gameserver.redisserver.repository;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.ssinhwa.gameserver.redisserver.entity.PeriodicData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class PeriodicDataRepositoryImpl implements PeriodicDataRepository {
    private static final List<PeriodicData> memoryPeriodicDataRepository = new ArrayList<>();

    @Override
    public void save(PeriodicData data) {
        log.info("Periodic data save 완료");
        memoryPeriodicDataRepository.add(data);
    }

    @Override
    public JsonElement getAll() {
        return new Gson().toJsonTree(memoryPeriodicDataRepository);
    }

    @Override
    public void deleteAll() {
        memoryPeriodicDataRepository.clear();
    }
}
