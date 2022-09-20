package com.ssinhwa.gameserver.redisserver.controller;

import com.ssinhwa.gameserver.redisserver.repository.PeriodicDataRepository;
import com.ssinhwa.gameserver.redisserver.service.MapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MapController {

    private final MapService mapService;
    private final PeriodicDataRepository periodicDataRepository;

    @GetMapping("/map/{id}")
    public String getMapById(@PathVariable String id) {
        return mapService.getMapById(id);
    }

    @PostMapping("/map/{id}")
    public void addMap(@PathVariable String id, @RequestBody String map) {
        mapService.addMap(map, id);
        log.info("Map Id : " + id + " 맵 추가");
        // periodicDataRepository.save(new PeriodicData(1, 2, null));
    }

    @PostMapping("/memoryMap/{id}")
    public void addMemoryMap(@PathVariable String id, @RequestBody String map) {
        mapService.addMemory(map, id);
        log.info("Map Id : " + id + "메모리에 맵 추가");
        // periodicDataRepository.save(new PeriodicData(1, 2, null));
    }
}
