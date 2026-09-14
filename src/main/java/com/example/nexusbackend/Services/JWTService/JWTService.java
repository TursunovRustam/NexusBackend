package com.example.nexusbackend.Services.JWTService;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JWTService {

    /**
     * Генерирует JWT токен для указанного пользователя.
     */
    String generateToken(UserDetails userDetails);

    /**
     * Генерирует JWT токен с дополнительными claims.
     */
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    /**
     * Генерирует refresh токен.
     */
    String generateRefreshToken(UserDetails userDetails);

    /**
     * Извлекает username (subject) из токена.
     */
    String extractUsername(String token);

    /**
     * Извлекает конкретный claim из токена.
     */
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    /**
     * Проверяет валидность токена для конкретного пользователя.
     */
    boolean isTokenValid(String token, UserDetails userDetails);

    /**
     * Проверяет, истёк ли срок действия токена.
     */
    boolean isTokenExpired(String token);
}