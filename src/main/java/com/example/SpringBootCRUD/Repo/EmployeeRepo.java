package com.example.SpringBootCRUD.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.SpringBootCRUD.model.Employee;
import com.example.SpringBootCRUD.model.Admin;

public interface EmployeeRepo extends MongoRepository<Employee, Integer> {
	 Optional<Admin> findByEmail(String email);
	 List<Employee> findTop5ByOrderByCreatedAtDesc();
}
