package com.example.SpringBootCRUD.Repo;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.SpringBootCRUD.model.Admin;

import java.util.Optional;

public interface UserRepo extends MongoRepository<Admin, Integer> {
    Optional<Admin> findByEmail(String email);
}
