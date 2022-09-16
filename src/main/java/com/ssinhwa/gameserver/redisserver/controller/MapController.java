package com.ssinhwa.gameserver.redisserver.controller;

import com.ssinhwa.gameserver.redisserver.aspect.LogExecutionTime;
import com.ssinhwa.gameserver.redisserver.entity.Map;
import com.ssinhwa.gameserver.redisserver.entity.PeriodicData;
import com.ssinhwa.gameserver.redisserver.repository.PeriodicDataRepository;
import com.ssinhwa.gameserver.redisserver.repository.SQLMapRepository;
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
    private final SQLMapRepository sqlMapRepository;

    @LogExecutionTime
    @GetMapping("/map/{id}")
    public String getMapById(@PathVariable String id) {
        return mapService.getMapById(id);
    }

    @LogExecutionTime
    @PostMapping("/map/{id}")
    public void addMap(@PathVariable String id, @RequestBody String map) {
        mapService.addMap(map, id);
        periodicDataRepository.save(new PeriodicData(1, 2, null));
    }

    @LogExecutionTime
    @GetMapping("/sqlmap/{id}")
    public String getMapFromSQLById(@PathVariable Long id) {
        return sqlMapRepository.findByFileId(id).getValue();
    }

    @LogExecutionTime
    @PostMapping("/sqlmap/{id}")
    public void addMapFromSQL(@PathVariable Long id, @RequestBody String map) {
        Map newMap = new Map();
        newMap.setValue(map);
        newMap.setFileId(id);
        sqlMapRepository.save(newMap);
    }
}
