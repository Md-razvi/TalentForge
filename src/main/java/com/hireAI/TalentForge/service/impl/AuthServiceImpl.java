package com.hireAI.TalentForge.service.impl;

import com.hireAI.TalentForge.dto.auth.AuthResponse;
import com.hireAI.TalentForge.dto.auth.LoginRequest;
import com.hireAI.TalentForge.dto.auth.RegisterRequest;
import com.hireAI.TalentForge.entity.User;
import com.hireAI.TalentForge.repository.UserRepository;
import com.hireAI.TalentForge.service.Interface.AuthService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public  AuthServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
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
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        User savedUser=userRepository.save(user);
        //For now Id is the token here until we complete the project
        return new AuthResponse("User has been registered successfully","userTokenID :"+String.valueOf(savedUser.getId()),savedUser.getRole());



    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Optional<User> user_email=userRepository.findByEmail(request.getEmail());
        if (user_email.isEmpty()){
            throw new RuntimeException("Email not registered");
        }
        User user=user_email.get();
        String password=request.getPassword();
        if(!user.getPassword().equals(password)){
            throw new RuntimeException("Please retry the password");
        }
        return new AuthResponse("Login Successfull","userTokenID :"+String.valueOf(user.getId()),user.getRole());
    }
}
