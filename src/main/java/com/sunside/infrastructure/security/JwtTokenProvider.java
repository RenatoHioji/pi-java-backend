package com.sunside.infrastructure.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtTokenProvider {


    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private Long jwtExpiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Instant now = Instant.now();
        Instant expiry = now.plus(Duration.ofMillis(jwtExpiration));

        return Jwts.builder()
                .subject(username)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiry))
                .signWith(getSigningKey())
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parser() // In 0.12.6, `Jwts.parser()` is preferred over `parserBuilder()`
                .verifyWith(getSigningKey()) // New API: `verifyWith()` replaces `setSigningKey()`
                .build()
                .parseSignedClaims(token) // New API: `parseSignedClaims()` replaces `parseClaimsJws()`
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(this.getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new IllegalArgumentException("Invalid JWT Token");
        } catch (ExpiredJwtException ex) {
            throw new IllegalArgumentException("Expired JWT Token");
        } catch (UnsupportedJwtException ex) {
            throw new IllegalArgumentException("Unsupported JWT Token");
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("JWT claims string is empty");
        }
    }
}
