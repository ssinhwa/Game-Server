package com.ssinhwa.gameserver.redisserver.repository;

import com.ssinhwa.gameserver.redisserver.dto.PlayerContinuousData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryPlayerContinuousDataRepository implements PlayerContinuousDataRepository {
    private static List<Map<String, PlayerContinuousData>> memory = new ArrayList<>();

    @Override
    public void save(PlayerContinuousData data) {
        Map<String, PlayerContinuousData> map = new HashMap<>();
        map.put("playerContinuousData", data);
        memory.add(map);
    }

    @Override
    public List<Map<String, PlayerContinuousData>> getAll() {
        return memory;
    }

    public void deleteAll() {
        memory.clear();
    }
}
