package com.hireAI.TalentForge.service.impl;

import com.hireAI.TalentForge.dto.auth.AuthResponse;
import com.hireAI.TalentForge.dto.auth.LoginRequest;
import com.hireAI.TalentForge.dto.auth.RegisterRequest;
import com.hireAI.TalentForge.entity.User;
import com.hireAI.TalentForge.repository.UserRepository;
import com.hireAI.TalentForge.service.Interface.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;
    public  AuthServiceImpl(UserRepository userRepository,
                            PasswordEncoder passwordEncoder,
                            JwtServiceImpl jwtService){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtService=jwtService;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        User user= new User();
        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastName());

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        user.setEmail(request.getEmail());
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }
        String encodedPassword=passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(request.getRole());
        User savedUser=userRepository.save(user);
        //For now Id is the token here until we complete the project
        return new AuthResponse("User has been registered successfully","userTokenID :"+String.valueOf(savedUser.getId()),savedUser.getRole());



    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Optional<User> optionalUser=userRepository.findByEmail(request.getEmail());
        if (optionalUser.isEmpty()){
            throw new RuntimeException("Email not registered");
        }
        User user=optionalUser.get();
        String password=request.getPassword();
        boolean isPasswordMatched=passwordEncoder.matches(password,user.getPassword());
        if(!isPasswordMatched){
            throw new RuntimeException("Please retry the password");
        }
        String token=jwtService.generateToken(user);
        return new AuthResponse("Login Success full",token,user.getRole());
    }
}
