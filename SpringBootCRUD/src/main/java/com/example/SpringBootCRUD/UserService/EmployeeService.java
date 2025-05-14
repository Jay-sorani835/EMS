package com.example.SpringBootCRUD.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBootCRUD.Repo.EmployeeRepo;
import com.example.SpringBootCRUD.model.Employee;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo repo;

    public List<Employee> getAll() {
        return repo.findAll();
    }

    public Employee save(Employee emp) {
        return repo.save(emp);
    }

    public Employee update(int id, Employee updated) {
        Employee emp = repo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        emp.setFname(updated.getFname());
        emp.setLname(updated.getLname());
        emp.setEmail(updated.getEmail());
        emp.setMobile(updated.getMobile());
        emp.setGender(updated.getGender());
        emp.setPassword(updated.getPassword());
        return repo.save(emp);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
    public Optional<Employee> findById(int id) {
    	return repo.findById(id);
    }
}
