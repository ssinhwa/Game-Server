package com.ssinhwa.gameserver.redisserver.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface MapRepository {
    public void save(String map, String id);

    public String getMapById(String id);

    public void memorySave(String map, String id);
}
