package com.example.SpringBootCRUD.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBootCRUD.Repo.UserRepo;
import com.example.SpringBootCRUD.model.Admin;

import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepo userRepo;

    public String changePassword(String oldPassword, String newPassword, String confirmPassword, int id, String email) { 
        Optional<Admin> userOpt = userRepo.findByEmail(email);
        if (!userOpt.isPresent()) {
            return "User not found";
        }

        Admin user = userOpt.get();

        if (!oldPassword.equals(user.getPassword())) {
            return "Old password is incorrect";
        }

        user.setPassword(newPassword);
        userRepo.save(user);

        return "Password changed successfully";
    }
}
