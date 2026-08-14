package com.hireAI.TalentForge.controller;

import com.hireAI.TalentForge.dto.auth.AuthResponse;
import com.hireAI.TalentForge.dto.auth.LoginRequest;
import com.hireAI.TalentForge.dto.auth.RegisterRequest;
import com.hireAI.TalentForge.service.Interface.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService){
        this.authService=authService;
    }
    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest registerRequest){
        return authService.register(registerRequest);
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

}
