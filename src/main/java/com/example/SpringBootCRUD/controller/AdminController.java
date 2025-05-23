package com.example.SpringBootCRUD.controller;

import com.example.SpringBootCRUD.model.Admin;
import com.example.SpringBootCRUD.model.Employee;

import jakarta.servlet.http.HttpSession;

import com.example.SpringBootCRUD.Repo.AdminRepo;
import com.example.SpringBootCRUD.UserService.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "https://ems-frontend-one-tawny.vercel.app")
public class AdminController {

	@Autowired
    private final AdminRepo adminRepository;
    private final AdminService adminService;

    public AdminController(AdminRepo adminRepository, AdminService adminService) {
        this.adminRepository = adminRepository;
		this.adminService = adminService;
    }

	@PostMapping("/add")
    public ResponseEntity<Admin> createEmployee(@RequestBody Admin admin) {
        Admin saveAdmin = adminService.save(admin);
        return ResponseEntity.ok(saveAdmin);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin loginRequest) {
    	System.out.println("AdminRepository is: " + adminRepository); // should not be null
        Admin admin = adminRepository.findByEmail(loginRequest.getEmail());
	
        if (admin != null && admin.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
	}
    }
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody Admin request) {

        if (request.getOldPassword() == null || request.getOldPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("{\"message\":\"Old password is required.\"}");
        }
	if (request.getNewPassword() == null || request.getNewPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("{\"message\":\"New password is required.\"}");
        }
	
        String result = adminService.changePassword(
                request.getOldPassword(),
                request.getNewPassword(),
                1,
                request.getEmail()
        );

        if (result.equals("Password changed successfully")) {
            return ResponseEntity.ok("{\"message\":\"" + result + "\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"message\":\"" + result + "\"}");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // Clears session on server
        return ResponseEntity.ok("Logout successful");
    }
    
    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser(HttpSession session) {
        Employee user = (Employee) session.getAttribute("user");
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No active session");
        }
    }

}
