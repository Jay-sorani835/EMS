package com.example.SpringBootCRUD.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBootCRUD.Repo.AdminRepo;
import com.example.SpringBootCRUD.model.Admin;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;

    @SuppressWarnings("null")
	public String changePassword(String oldPassword, String newPassword, String confirmPassword, int id, String email) { 
        Admin admin = adminRepo.findByEmail(email);
        if (admin!=null) {
            return "User not found";
        }

        if (!oldPassword.equals(admin.getPassword())) {
            return "Old password is incorrect";
        }

        admin.setPassword(newPassword);
        adminRepo.save(admin);

        return "Password changed successfully";
    }
}
