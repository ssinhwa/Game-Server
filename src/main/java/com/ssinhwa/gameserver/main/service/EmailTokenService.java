package com.ssinhwa.gameserver.main.service;

import com.ssinhwa.gameserver.main.entity.EmailToken;
import com.ssinhwa.gameserver.main.repository.EmailTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailTokenService {
    private static final long EMAIL_TOKEN_EXPIRATION_TIME = 5L;

    private final EmailSenderService emailSenderService;
    private final EmailTokenRepository emailTokenRepository;

    @Value("${email.host}")
    private String emailLink;

    public void createEmailConfirmationToken(String userId, String email) {
        EmailToken emailToken = new EmailToken(userId);
        emailTokenRepository.save(emailToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("이메일 인증 요청 안내 메일입니다.");
        mailMessage.setText("http://" + emailLink + "/api/confirm-email?token=" + emailToken.getId());

        emailSenderService.sendEmail(mailMessage);
        log.info("이메일 전송 : " + mailMessage);
    }

    public EmailToken findByIdAndExpirationDate(String emailTokenId) {
        Optional<EmailToken> emailToken = emailTokenRepository
                .findByIdAndExpirationDateAfterAndExpired
                        (emailTokenId, LocalDateTime.now().minusMinutes(EMAIL_TOKEN_EXPIRATION_TIME), false);
        return emailToken
                .orElseThrow(() -> new EntityNotFoundException("Token Not Found"));
    }
}
