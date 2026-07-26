package com.hireAI.TalentForge.service.Interface;

import com.hireAI.TalentForge.dto.auth.AuthResponse;
import com.hireAI.TalentForge.dto.auth.LoginRequest;
import com.hireAI.TalentForge.dto.auth.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
