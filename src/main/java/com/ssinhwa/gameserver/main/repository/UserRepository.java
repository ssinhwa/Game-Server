package com.ssinhwa.gameserver.main.repository;

import com.ssinhwa.gameserver.main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
