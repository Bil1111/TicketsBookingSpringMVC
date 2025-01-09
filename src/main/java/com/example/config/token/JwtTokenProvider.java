package com.example.config.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private final String jwtSecret = "4f9e0a5b42a04de2a7f1e1b77b4b2bc4";

    // Генерація токена
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // Додаємо email як payload
                .setIssuedAt(new Date()) // Час створення
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes())) // Підпис токена
                .compact();
    }

    public String getEmailFromToken(String token) {
        try {
            Claims claims = Jwts.parser()// Використовуємо parserBuilder замість parser
                    .setSigningKey(jwtSecret.getBytes()) // Вказуємо секретний ключ для перевірки підпису
                    .build()
                    .parseClaimsJws(token) // Розбираємо токен
                    .getBody(); // Отримуємо тіло токена
            return claims.getSubject(); // Витягуємо email з поля "subject"
        } catch (Exception e) {
            // Обробка помилок
            throw new IllegalArgumentException("Invalid JWT token", e);
        }
    }

    public boolean checkUser(String token, String email) {
        String tEmail = getEmailFromToken(token);
        return tEmail.equals(email);
    }
}
