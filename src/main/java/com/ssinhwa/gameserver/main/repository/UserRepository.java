package com.ssinhwa.gameserver.main.repository;

import com.ssinhwa.gameserver.main.entity.CustomUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CustomUserDetails, Long> {
    public CustomUserDetails findCustomUserDetailsByUsername(String username);

    public CustomUserDetails findCustomUserDetailsById(Long id);
}
