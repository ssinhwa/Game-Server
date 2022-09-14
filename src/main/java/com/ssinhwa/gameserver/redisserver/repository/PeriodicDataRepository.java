package com.ssinhwa.gameserver.redisserver.repository;

import com.google.gson.JsonElement;
import com.ssinhwa.gameserver.redisserver.dto.PeriodicData;

public interface PeriodicDataRepository {
    public void save(PeriodicData data);

    public JsonElement getAll();
}
