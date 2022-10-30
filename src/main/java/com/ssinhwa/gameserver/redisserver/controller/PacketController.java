package com.ssinhwa.gameserver.redisserver.controller;

import com.ssinhwa.gameserver.redisserver.entity.PeriodicData;
import com.ssinhwa.gameserver.redisserver.entity.PlayerContinuousData;
import com.ssinhwa.gameserver.redisserver.service.PacketService;
import com.ssinhwa.gameserver.redisserver.service.PdlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PacketController {
    private final PacketService packetService;
    private final PdlService pdlService;

    // save 만 하면 Task 에서 뿌려줄 것
    @MessageMapping("/continuousData")
    public void sendPacket(@RequestBody PlayerContinuousData playerContinuousData) {
        packetService.saveContinuousData(playerContinuousData);
    }

    @MessageMapping("/periodicData")
    public void sendPeriodicData(@RequestBody PeriodicData periodicData) {
        pdlService.save(periodicData);
        log.info("Periodic Data 추가");
    }
}
