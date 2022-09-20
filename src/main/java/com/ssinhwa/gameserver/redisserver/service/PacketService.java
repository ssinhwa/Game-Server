package com.ssinhwa.gameserver.redisserver.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ssinhwa.gameserver.redisserver.entity.PlayerContinuousData;
import com.ssinhwa.gameserver.redisserver.repository.PeriodicDataRepository;
import com.ssinhwa.gameserver.redisserver.repository.PlayerContinuousDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PacketService {
    private final Gson gson;
    private final PlayerContinuousDataRepository playerContinuousDataRepository;
    private final PeriodicDataRepository periodicDataRepository;

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
