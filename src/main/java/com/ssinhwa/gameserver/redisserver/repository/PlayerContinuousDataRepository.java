package com.ssinhwa.gameserver.redisserver.repository;

import com.google.gson.JsonElement;
import com.ssinhwa.gameserver.redisserver.dto.PlayerContinuousData;

public interface PlayerContinuousDataRepository {
    public void save(PlayerContinuousData data);

    public JsonElement getAll();
}
