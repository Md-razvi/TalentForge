package com.hireAI.TalentForge.security;

import com.hireAI.TalentForge.entity.User;
import com.hireAI.TalentForge.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public record CustomUserDetailService(UserRepository userRepository) implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> loadedName = userRepository.findByEmail(username);
        if (loadedName.isEmpty()) {
            throw new UsernameNotFoundException("The given username is not provided");
        }
        User userdetail = loadedName.get();
        return new CustomUserDetails(userdetail);
    }
}
