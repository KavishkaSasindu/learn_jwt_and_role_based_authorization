package com.example.JwtAnRolePermission.service.auth;

import com.example.JwtAnRolePermission.jwt.JwtService;
import com.example.JwtAnRolePermission.model.Role;
import com.example.JwtAnRolePermission.model.UserModel;
import com.example.JwtAnRolePermission.repo.UserRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    //    register user
    public UserModel registerUser(UserModel userModel) {
        Optional<UserModel> existUser = userRepo.findByEmail(userModel.getEmail());
        if (existUser.isPresent()) {
            return null;
        }
        userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
        userModel.setRole(Role.USER);
        return userRepo.save(userModel);

    }

//    login user
    public String loginUser(UserModel userModel) {
        Optional<UserModel> existUser = userRepo.findByEmail(userModel.getEmail());
        if(existUser.isPresent()) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userModel.getEmail(), userModel.getPassword()));
            if(authentication.isAuthenticated()) {
                Map<String, Object> claims = new HashMap<>();
                return jwtService.generateToken( claims, userModel);
            }
        }
        return null;
    }
}
