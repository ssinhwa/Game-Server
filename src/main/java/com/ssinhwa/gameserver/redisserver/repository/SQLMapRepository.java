package com.ssinhwa.gameserver.redisserver.repository;

import com.ssinhwa.gameserver.redisserver.entity.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SQLMapRepository extends JpaRepository<Map, Long> {
    public Map findByFileId(Long fileId);
}
