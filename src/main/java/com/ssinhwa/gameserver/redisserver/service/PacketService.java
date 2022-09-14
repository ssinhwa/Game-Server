package com.ssinhwa.gameserver.redisserver.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ssinhwa.gameserver.redisserver.dto.PlayerContinuousData;
import com.ssinhwa.gameserver.redisserver.repository.MemoryPeriodicDataRepository;
import com.ssinhwa.gameserver.redisserver.repository.MemoryPlayerContinuousDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PacketService {
    private final Gson gson;
    private final MemoryPlayerContinuousDataRepository playerContinuousDataRepository;
    private final MemoryPeriodicDataRepository periodicDataRepository;

    public JsonObject getPacketList() {
        JsonElement continuousDataJson = playerContinuousDataRepository.getAll();
        JsonElement periodicDataListJson = periodicDataRepository.getAll();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("cdl", continuousDataJson);
        jsonObject.add("pdl", periodicDataListJson);
        return jsonObject;
    }

    public String jsonToString() {
        return getPacketList().toString();
    }

    public void saveContinuousData(PlayerContinuousData data) {
        playerContinuousDataRepository.save(data);
    }
}
