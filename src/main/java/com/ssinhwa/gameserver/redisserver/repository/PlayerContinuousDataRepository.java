package com.ssinhwa.gameserver.redisserver.repository;

import com.ssinhwa.gameserver.redisserver.dto.PlayerContinuousData;

import java.util.List;
import java.util.Map;

public interface PlayerContinuousDataRepository {
    public void save(PlayerContinuousData data);

    public List<Map<String, PlayerContinuousData>> getAll();
}
