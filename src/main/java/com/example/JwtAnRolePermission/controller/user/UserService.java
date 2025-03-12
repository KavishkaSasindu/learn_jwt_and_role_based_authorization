package com.example.JwtAnRolePermission.controller.user;

import com.example.JwtAnRolePermission.repo.UserRepo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepo userRepo;

//    test route
    public String getHello() {
        return "Hello authorize user";
    }
}
