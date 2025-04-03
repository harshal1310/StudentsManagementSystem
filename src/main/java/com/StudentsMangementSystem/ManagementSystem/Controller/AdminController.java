package com.StudentsMangementSystem.ManagementSystem.Controller;

import com.StudentsMangementSystem.ManagementSystem.DTO.AdminDTO;
import com.StudentsMangementSystem.ManagementSystem.Service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody AdminDTO adminDTO) {
        if (adminDTO.getUsername() == null || adminDTO.getPassword() == null) {
            return ResponseEntity.badRequest().body("Username and password cannot be null");
        }
        if (adminDTO.getUsername().isEmpty() || adminDTO.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Username and password cannot be empty");
        }
        if (adminService.validateAdmin(adminDTO.getUsername(), adminDTO.getPassword())) {
            return ResponseEntity.badRequest().body("Admin already exists");
        }
        System.out.println("in register");
        adminService.registerAdmin(adminDTO.getUsername(), adminDTO.getPassword());
        return ResponseEntity.ok("Admin registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AdminDTO adminDTO) {
        if (adminService.validateAdmin(adminDTO.getUsername(),adminDTO.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
