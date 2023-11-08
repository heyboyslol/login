package com.ssafy.mockstockinvestment.user.domain;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    @Value("${jwt.key}")
    private String secretKey; // 비밀키~

    @Value("${jwt.accessTokenExpirationTime}")
    private Long accessTokenExpirationTime; // 엑세스 토큰 유효기간

    @Value("${jwt.refreshTokenExpirationTime}")
    private Long refreshTokenExpirationTime; // 리프레시 토큰 유효기간

    public String generateAccessToken(String subject) {
        return generateToken(subject, accessTokenExpirationTime);
    }

    private String generateToken(String subject, Long expirationTime) {
        final Date now = new Date();
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expirationTime))
                .signWith(generateKey(secretKey), SignatureAlgorithm.HS512)
                .compact();
    }

    private Key generateKey(String secret) {
        byte[] accessKeyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(accessKeyBytes);
    }

    public String generateRefreshToken() {
        return generateToken(UUID.randomUUID().toString(), refreshTokenExpirationTime);
    }

    public void validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("토큰이 만료되었다능!");
        } catch (JwtException e) {
            throw new RuntimeException("유효하지 않은 토큰이라능!");
        }
    }

}
