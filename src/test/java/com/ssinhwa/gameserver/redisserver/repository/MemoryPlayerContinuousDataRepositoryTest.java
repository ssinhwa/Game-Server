package com.ssinhwa.gameserver.redisserver.repository;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ssinhwa.gameserver.redisserver.entity.PlayerContinuousData;
import com.ssinhwa.gameserver.redisserver.entity.WPosition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
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
        JsonElement all = repository.getAll();
        JsonObject packetList = new JsonObject();
        packetList.add("cdl", all);
        System.out.println(packetList);
    }

    public JsonObject getPacketList(List<Map<String, PlayerContinuousData>> continuousDataList) {
        Gson gson = new Gson();
        JsonObject packetListJson = new JsonObject();
        JsonElement continuousDataListJson = gson.toJsonTree(continuousDataList);
        JsonElement periodicDataListJson = gson.toJsonTree(""); // periodicDataList 들어갈 자리
        List<Map<String, JsonElement>> packetList = new ArrayList<>();
        Map<String, JsonElement> map = new HashMap<>();
        map.put("continuousDataList", continuousDataListJson);
        map.put("periodicDataList", periodicDataListJson);
        packetList.add(map);
        packetListJson.add("packetList", gson.toJsonTree(packetList));
        return packetListJson;
    }
}