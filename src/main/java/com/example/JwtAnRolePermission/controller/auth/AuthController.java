package com.example.JwtAnRolePermission.controller.auth;

import com.example.JwtAnRolePermission.model.UserModel;
import com.example.JwtAnRolePermission.service.auth.AuthService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Data
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

//    register User
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserModel userModel) {
        try{
            UserModel user = authService.registerUser(userModel);
            if(user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user already exists");
            }
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

//    login user
    @PostMapping("/login")
    public ResponseEntity<?> logInUser(@RequestBody UserModel userModel) {
        try{
            String returnValue = authService.loginUser(userModel);
            if(returnValue == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("login failed");
            }
            return ResponseEntity.status(HttpStatus.OK).body(returnValue);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
