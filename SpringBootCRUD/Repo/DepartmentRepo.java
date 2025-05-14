package com.example.SpringBootCRUD.Repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.SpringBootCRUD.model.Departments;

public interface DepartmentRepo extends MongoRepository<Departments, String> {
    List<Departments> findAll();
}
