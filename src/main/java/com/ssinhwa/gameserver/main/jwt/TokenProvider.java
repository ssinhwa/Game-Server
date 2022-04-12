package com.ssinhwa.gameserver.main.jwt;

// https://bcp0109.tistory.com/301
// https://daddyprogrammer.org/post/5072/spring-websocket-chatting-server-spring-security-jwt/
// JWT 토큰에 관련된 암호화, 복호화 검증 로직이 이루어지느 ㄴ곳

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000L * 60 * 60;   // 유효시간은 1시간
    private static final String AUTHORITIES_KEY = "auth";

    @Value("${jwt.secret}")
    private String secretKey;

    // 이름으로 JWT Token 생성
    public String generateToken(Authentication authentication) {

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        return Jwts.builder()
                .setId(authentication.getName())    // "Id" : "name"
                .claim(AUTHORITIES_KEY, authorities)    // "auth" : "ROLE_USER"
                .setExpiration(new Date(now + ACCESS_TOKEN_EXPIRE_TIME))    // "exp" : "123123~"
                .signWith(SignatureAlgorithm.HS256, secretKey)  // "alg" : "HS256"
                .compact();
    }

    // 복호화 하여 username 얻는다.
    public String getUserNameFromJwt(String jwt) {
        Claims claim = getClaims(jwt).getBody();
        if (claim.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
        return claim.getId();
    }

    // 유효성 체크
    public boolean validateToken(String jwt) {
        return (this.getClaims(jwt) != null);
    }

    // Error handling
    private Jws<Claims> getClaims(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwt);
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
