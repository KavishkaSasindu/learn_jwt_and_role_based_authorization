package com.example.JwtAnRolePermission.service;

import com.example.JwtAnRolePermission.model.UserModel;
import com.example.JwtAnRolePermission.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;

    @Autowired
    public MyUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> userDetails = userRepo.findByEmail(username);
        if(userDetails.isEmpty()) {
            throw new UsernameNotFoundException(username+ " not found");
        }
        return userDetails.get();
    }
}
