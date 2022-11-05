package com.ssinhwa.gameserver.redisserver.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ssinhwa.gameserver.redisserver.entity.PlayerContinuousData;
import com.ssinhwa.gameserver.redisserver.repository.PeriodicDataRepository;
import com.ssinhwa.gameserver.redisserver.repository.PlayerContinuousDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PacketService {
    private final PlayerContinuousDataRepository playerContinuousDataRepository;
    private final PeriodicDataRepository periodicDataRepository;

    @Transactional
    public JsonObject getPacketList() {
        JsonElement continuousDataJson = playerContinuousDataRepository.getAll();
        JsonElement periodicDataListJson = periodicDataRepository.getAll();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("cdl", continuousDataJson);
        jsonObject.add("pdl", periodicDataListJson);
        return jsonObject;
    }

    @Transactional
    public JsonElement pdlJson() {
        return periodicDataRepository.getAll();
    }

    @Transactional
    public JsonElement cdlJson() {
        return playerContinuousDataRepository.getAll();
    }

    @Transactional
    public void deletePcdAll() {
        playerContinuousDataRepository.deleteAll();
    }

    @Transactional
    public void deletePddAll() {
        periodicDataRepository.deleteAll();
    }

    @Transactional
    public void saveContinuousData(PlayerContinuousData data) {
        playerContinuousDataRepository.save(data);
    }
}
