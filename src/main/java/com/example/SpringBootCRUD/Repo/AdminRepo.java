package com.example.SpringBootCRUD.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBootCRUD.model.Admin;

@Repository
public interface AdminRepo extends MongoRepository<Admin, Integer> {
    Admin findByEmail(String email);
}
