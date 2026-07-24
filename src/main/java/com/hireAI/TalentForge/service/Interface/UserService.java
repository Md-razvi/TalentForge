package com.hireAI.TalentForge.service.Interface;

import com.hireAI.TalentForge.entity.User;

import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long Id);


}
