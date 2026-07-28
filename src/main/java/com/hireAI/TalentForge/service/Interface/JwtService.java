package com.hireAI.TalentForge.service.Interface;

import com.hireAI.TalentForge.entity.User;

public interface JwtService {
    public String generateToken(User user);
}
