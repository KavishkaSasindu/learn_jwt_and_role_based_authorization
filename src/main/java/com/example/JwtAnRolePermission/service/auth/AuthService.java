package com.example.JwtAnRolePermission.service.auth;

import com.example.JwtAnRolePermission.model.Role;
import com.example.JwtAnRolePermission.model.UserModel;
import com.example.JwtAnRolePermission.repo.UserRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

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
}
