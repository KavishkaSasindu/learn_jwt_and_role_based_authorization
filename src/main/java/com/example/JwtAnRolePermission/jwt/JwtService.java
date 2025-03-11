package com.example.JwtAnRolePermission.jwt;

import com.example.JwtAnRolePermission.model.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Data
@Service
@NoArgsConstructor
public class JwtService {

    @Value("${mySecretKey}")
    private String secretKey;

//    generate a key
    public SecretKey generateKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

//    generate jwt token
    public String generateToken(Map<String, Object> claims , UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .signWith(generateKey(),Jwts.SIG.HS256)
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60))
                .compact();
    }

//    validate token
    public boolean isValidateToken(String token,UserDetails userDetails) {
        String username = extractUsername(token);
        return username != null && username.equals(userDetails.getUsername());

    }

//    extract username from token
    public String extractUsername(String token) {
        return extractOneClaims(token, Claims::getSubject);
    }

//    extract claims
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

//    extract one claim from token
    public <T> T extractOneClaims(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }
}
