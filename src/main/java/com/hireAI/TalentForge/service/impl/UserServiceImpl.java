package com.hireAI.TalentForge.service.impl;
import com.hireAI.TalentForge.entity.User;
import com.hireAI.TalentForge.repository.UserRepository;
import com.hireAI.TalentForge.service.Interface.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public User save(User user) {
        User user1=userRepository.save(user);
        return  user1;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
