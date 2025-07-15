package com.tyrbropro.user_service.security.jwt;

import com.tyrbropro.user_service.dto.security.JwtAuthenticationDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtService {

    static final Logger LOGGER = LogManager.getLogger(JwtService.class);

    @Value("${jwt.secret}")
    String jwtSecret;

    public JwtAuthenticationDto generateAuthToken(String email)  {
        var jwtDTO = new JwtAuthenticationDto();
        jwtDTO.setToken(generateJwtToken(email));
        jwtDTO.setRefreshToken(generateRefreshToken(email));
        return jwtDTO;
    }

    public JwtAuthenticationDto refreshBaseToken(String email, String refreshToken) {
        var jwtDTO = new JwtAuthenticationDto();
        jwtDTO.setToken(generateJwtToken(email));
        jwtDTO.setRefreshToken(refreshToken);
        return jwtDTO;
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser().
                verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return true;
        } catch (ExpiredJwtException e) {
            LOGGER.error("Expired JWT token", e);
        } catch (UnsupportedJwtException e) {
            LOGGER.error("Unsupported JWT token", e);
        } catch (MalformedJwtException e) {
            LOGGER.error("Malformed JWT token", e);
        } catch (SecurityException e) {
            LOGGER.error("Security exception", e);
        } catch (Exception e) {
            LOGGER.error("Invalid token", e);
        }
        return false;
    }

    private String generateJwtToken(String email) {
        var date = Date.from(LocalDateTime.now().plusMinutes(1).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .subject(email)
                .expiration(date)
                .signWith(getSignInKey())
                .compact();
    }

    private String generateRefreshToken(String email) {
        var date = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .subject(email)
                .expiration(date)
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey() {
        byte[] encodedKey = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(encodedKey);
    }
}
