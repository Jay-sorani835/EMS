package com.example.SpringBootCRUD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.SpringBootCRUD.Repo.DepartmentRepo;
import com.example.SpringBootCRUD.model.Departments;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepo departmentRepository;

    // Fetch all departments
    @GetMapping("/all")
    public List<Departments> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Add a new department
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Departments addDepartment(@RequestBody Departments department) {
        return departmentRepository.save(department);
    }

    // Edit an existing department
    @PutMapping("/edit/{id}")
    public Departments editDepartment(@PathVariable String id, @RequestBody Departments department) {
        Departments existingDepartment = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
        existingDepartment.setName(department.getName());
        return departmentRepository.save(existingDepartment);
    }

    // Delete a department
    @DeleteMapping("/delete/{id}")
    public void deleteDepartment(@PathVariable String id) {
        departmentRepository.deleteById(id);
    }
}
