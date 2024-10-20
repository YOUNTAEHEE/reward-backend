package com.yoon.reward.jwt;
import com.yoon.reward.user.command.domain.aggregate.UserRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {
    private SecretKey secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}")String secret) {

        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }
    public String getUserId(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("userId", String.class);
    }

    public UserRole getRole(String token) {

        String roleName = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
        return UserRole.valueOf(roleName);
    }

    public Boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

//    public String createJwt(String userId, UserRole role, Long expiredMs) {
//
//        return Jwts.builder()
//                .claim("userId", userId)
//                .claim("role", role)
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis() + expiredMs))
//                .signWith(secretKey)
//                .compact();
//    }
    public String createJwt(String userId, UserRole role, Long expiredMs) {
        try {
            String token = Jwts.builder()
                    .claim("userId", userId)
                    .claim("role", role.name())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                    .signWith(secretKey, SignatureAlgorithm.HS256)
                    .compact();

            System.out.println("Generated Token: " + token); // 로그 추가
            return token;
        } catch (Exception e) {
            System.out.println("JWT creation failed: " + e.getMessage()); // 오류 발생 시 로그
            return null;
        }
    }
}
