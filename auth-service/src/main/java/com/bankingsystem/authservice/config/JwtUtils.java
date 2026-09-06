package com.bankingsystem.authservice.config;

import com.bankingsystem.authservice.entity.UserCredential;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${JWT_SECRET}")
    private String secret;

    @Value("${JWT_ACCESS_TOKEN_TTL_MS}")
    private long accessTokenTtlMs;

    public String generateToken(UserCredential user) {
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("role", user.getRole().getRoleName())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() +
                        accessTokenTtlMs))
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}