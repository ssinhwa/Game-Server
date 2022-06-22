package com.ssinhwa.gameserver.redisserver.repository;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.ssinhwa.gameserver.redisserver.dto.PlayerContinuousData;
import com.ssinhwa.gameserver.redisserver.dto.WPosition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class MemoryPlayerContinuousDataRepositoryTest {

    MemoryPlayerContinuousDataRepository repository = new MemoryPlayerContinuousDataRepository();

    @AfterEach
    public void afterEach() {
        repository.deleteAll();
    }

    @Test
    public void DataToJsonList() {
        WPosition wPosition = new WPosition(3.241f, 5.21f);
        PlayerContinuousData playerContinuousData1 = new PlayerContinuousData(0, 2, wPosition, 90f);
        PlayerContinuousData playerContinuousData2 = new PlayerContinuousData(1, 3, wPosition, 90f);

        repository.save(playerContinuousData1);
        repository.save(playerContinuousData2);
        System.out.println("repository = " + repository);

        List<Map<String, PlayerContinuousData>> all = repository.getAll();
        for (Map<String, PlayerContinuousData> map : all) {
            System.out.println(map.get("playerContinuousData").getPlayerId());
        }
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(all);
        System.out.println("jsonElement = " + jsonElement);
    }
}