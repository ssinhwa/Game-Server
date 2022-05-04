package com.ssinhwa.gameserver.main.jwt;

// https://bcp0109.tistory.com/301
// https://sol-devlog.tistory.com/22
// https://daddyprogrammer.org/post/5072/spring-websocket-chatting-server-spring-security-jwt/
// JWT 토큰에 관련된 암호화, 복호화 검증 로직이 이루어지느 ㄴ곳

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {
    private static final long TOKEN_EXPIRE_TIME = 1000L * 60;   // 유효시간은 1분

    @Value("${jwt.secret}")
    private String secretKey;

    // 이름으로 JWT Token 생성
    public String generateToken(String username) {


        Date nowDate = new Date();
        long now = nowDate.getTime();

        Date TokenExpiresIn = new Date(now + TOKEN_EXPIRE_TIME);

        return Jwts.builder()
                .setId(username)    // "Id" : "name"
                .setIssuedAt(nowDate)   // 토큰 발행 일자
                .setExpiration(TokenExpiresIn)    // "exp" : "123123~"
                .signWith(SignatureAlgorithm.HS256, secretKey)  // "alg" : "HS256"
                .compact();
    }

    // 복호화 하여 username 얻는다.
    public String getUserNameFromJwt(String jwt) {
        Claims claim = getClaims(jwt);
        return claim.getId();
    }

    // 유효성 체크
    public boolean validateToken(String jwt) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            log.error("잘못된 JWT 서명입니다.");
        }
        return false;
    }

    // Error handling
    private Claims getClaims(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwt).getBody();
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
            throw ex;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT Token");
            throw ex;
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT Token");
            throw ex;
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT Token");
            throw ex;
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty");
            throw ex;
        }
    }
}
