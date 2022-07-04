package com.ssinhwa.gameserver.redisserver.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ssinhwa.gameserver.redisserver.dto.PlayerContinuousData;
import com.ssinhwa.gameserver.redisserver.repository.MemoryPlayerContinuousDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PacketService {
    private final Gson gson;
    private final MemoryPlayerContinuousDataRepository repository;

    public JsonObject getPacketList() {
        List<Map<String, PlayerContinuousData>> continuousDataList = repository.getAll();
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

    public String jsonToString() {
        return getPacketList().toString();
    }

    public void saveContinuousData(PlayerContinuousData data) {
        repository.save(data);
    }
}
