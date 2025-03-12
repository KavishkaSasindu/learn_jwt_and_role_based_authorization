package com.example.JwtAnRolePermission.controller.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RequestMapping("/manager")
@RestController
public class ManagerController {

    @GetMapping("/getManager")
    public ResponseEntity<?> getAdmin() {
        return ResponseEntity.status(HttpStatus.OK).body("GET :: Get Manager");
    }

    @GetMapping("/createManager")
    public ResponseEntity<?> createAdmin() {
        return ResponseEntity.status(HttpStatus.OK).body("POST :: Create Manager");
    }
}
