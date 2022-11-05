package com.ssinhwa.gameserver.redisserver.repository;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.ssinhwa.gameserver.redisserver.entity.PeriodicData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PeriodicDataRepositoryImpl implements PeriodicDataRepository {
    private static final List<PeriodicData> memoryPeriodicDataRepository = Collections.synchronizedList(new ArrayList<>());
    private final Gson gson;

    @Override
    public synchronized void save(PeriodicData data) {
        memoryPeriodicDataRepository.add(data);
    }

    @Override
    public synchronized JsonElement getAll() {
        return gson.toJsonTree(memoryPeriodicDataRepository);
    }

    @Override
    public synchronized void deleteAll() {
        memoryPeriodicDataRepository.clear();
    }
}
