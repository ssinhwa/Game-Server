package com.ssinhwa.gameserver.main.repository;

import com.ssinhwa.gameserver.main.entity.EmailToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailTokenRepository extends JpaRepository<EmailToken, String> {
    public Optional<EmailToken> findByIdAndExpirationDateAfterAndExpired(String id, LocalDateTime expirationDate, boolean expired);
}
