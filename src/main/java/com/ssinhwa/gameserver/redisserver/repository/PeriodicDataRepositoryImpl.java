package com.ssinhwa.gameserver.redisserver.repository;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.ssinhwa.gameserver.redisserver.entity.PeriodicData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Slf4j
public class PeriodicDataRepositoryImpl implements PeriodicDataRepository {
    private static final List<PeriodicData> memoryPeriodicDataRepository = Collections.synchronizedList(new ArrayList<>());

    @Override
    @Transactional
    public void save(PeriodicData data) {
        memoryPeriodicDataRepository.add(data);
    }

    @Override
    @Transactional
    public JsonElement getAll() {
        return new Gson().toJsonTree(memoryPeriodicDataRepository);
    }

    @Override
    @Transactional
    public void deleteAll() {
        memoryPeriodicDataRepository.clear();
    }
}
