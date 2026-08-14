package com.hireAI.TalentForge.service.Interface;

import com.hireAI.TalentForge.entity.User;

import java.util.Date;

public interface JwtService {
     String generateToken(User user);
    String extractUsername(String token);
    boolean isTokenExpired(String token);
    Date extractExpiration(String token);
    boolean isTokenValid(String token ,User user);

}
