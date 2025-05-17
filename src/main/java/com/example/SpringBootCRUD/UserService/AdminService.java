package com.example.SpringBootCRUD.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBootCRUD.Repo.AdminRepo;
import com.example.SpringBootCRUD.model.Admin;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;

public Admin save(Admin admin) {
        return adminRepo.save(admin);
    }
    @SuppressWarnings("null")
	public String changePassword(String oldPassword, String newPassword, int id, String email) { 
        Admin admin = adminRepo.findByEmail(email);
        if (admin==null) {
            return "User not found";
        }

        if (!oldPassword.equals(admin.getNewPassword())) {
            return "Old password is incorrect";
        }

        admin.setNewPassword(newPassword);
        adminRepo.save(admin);

        return "Password changed successfully";
    }
}
