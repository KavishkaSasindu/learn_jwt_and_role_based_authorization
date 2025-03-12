package com.example.JwtAnRolePermission.controller.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RequestMapping("/admin")
@RestController
public class AdminController {

    @GetMapping("/getAdmin")
    public ResponseEntity<?> getAdmin() {
        return ResponseEntity.status(HttpStatus.OK).body("GET :: Get Admin");
    }

    @GetMapping("/createAdmin")
    public ResponseEntity<?> createAdmin() {
        return ResponseEntity.status(HttpStatus.OK).body("POST :: Create Admin");
    }
}
