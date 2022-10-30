package com.ssinhwa.gameserver.redisserver.service;

import com.ssinhwa.gameserver.redisserver.entity.PeriodicData;
import com.ssinhwa.gameserver.redisserver.repository.PeriodicDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PdlService {
    private final PeriodicDataRepository periodicDataRepository;

    public void save(PeriodicData periodicData) {
        periodicDataRepository.save(periodicData);
    }
}
