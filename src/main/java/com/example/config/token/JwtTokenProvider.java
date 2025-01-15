package com.example.config.token;

import com.example.config.customers.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;

import static javax.crypto.Cipher.SECRET_KEY;

@Component
public class JwtTokenProvider {
    private final Key jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    // Генерація токена
    public String generateToken(String email, Role role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role.name()) // Add the role as a string
                .setIssuedAt(new Date())
                .signWith(jwtSecret)  // Use the securely generated key
                .compact();
    }
    // Отримуємо ролі з токена
    public List<String> getRolesFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret) // Використовуємо вже наявний секретний ключ
                .build() // Будуємо парсер
                .parseClaimsJws(token) // Парсимо токен
                .getBody(); // Отримуємо Claims
        return claims.get("role", List.class); // "role" — ключ, під яким зберігається роль
    }

    // Отримуємо email з токена
    public String getEmailFromToken(String token) {
        try {
            // Розбираємо токен і отримуємо claims
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(jwtSecret) // Використовуємо вже наявний секретний ключ
                    .build() // Створюємо парсер
                    .parseClaimsJws(token) // Парсимо токен
                    .getBody(); // Отримуємо claims з токену

            // Повертаємо поле "subject" як email
            return claims.getSubject();
        } catch (Exception e) {
            // Обробка помилок, якщо токен недійсний або пошкоджений
            throw new IllegalArgumentException("Invalid JWT token", e);
        }
    }


    public boolean checkUser(String token, String email) {
        String tEmail = getEmailFromToken(token);
        return tEmail.equals(email);
    }
}
