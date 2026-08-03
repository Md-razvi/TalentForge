package com.hireAI.TalentForge.service.impl;

import com.hireAI.TalentForge.entity.User;
import com.hireAI.TalentForge.service.Interface.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;


    private  SecretKey getSignInKey(){
        byte[] keyBytes=Decoders.BASE64.decode(secretKey);
        return  Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String generateToken(User user){
        // creating a subject using jwt builder pattern
        return Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+jwtExpiration))
                .signWith(getSignInKey())
                .compact();

    }
    private Claims extractAllClaims(String token){

        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaims(String token, Function<Claims,T> claimresolver){
        Claims claim=extractAllClaims(token);
        return claimresolver.apply(claim);
    }

    public String extractUsername(String token){
        return  extractClaims(token,Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaims(token,Claims::getExpiration);
    }
    public boolean isTokenExpired(String token){
        Date current=new Date();
        Date expiration=extractExpiration(token);
        return expiration.before(current);
    }
    public boolean isTokenValid(String token ,User user){
        String tokenMail=extractUsername(token);
        String userMail=user.getEmail();
        return (tokenMail.equals(userMail) && !isTokenExpired(token));
    }
}
