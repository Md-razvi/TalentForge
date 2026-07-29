package com.hireAI.TalentForge.service.impl;

import com.hireAI.TalentForge.entity.User;
import com.hireAI.TalentForge.service.Interface.JwtService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;


    private  Key getSignInKey(){
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
}
