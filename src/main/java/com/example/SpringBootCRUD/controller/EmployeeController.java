package com.example.SpringBootCRUD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.SpringBootCRUD.Repo.EmployeeRepo;
import com.example.SpringBootCRUD.UserService.EmployeeService;
import com.example.SpringBootCRUD.UserService.SequenceGeneratorService;
import com.example.SpringBootCRUD.model.Employee;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "https://ems-frontend-one-tawny.vercel.app/")
public class EmployeeController {
	@Autowired
	private EmployeeRepo empRepo;
	private final EmployeeService empService;
	

    private final SequenceGeneratorService sequenceGeneratorService;

    public EmployeeController(SequenceGeneratorService sequenceGeneratorService, EmployeeService empService) {
    	this.empService = empService;
		this.sequenceGeneratorService = sequenceGeneratorService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = empRepo.findAll();
        return ResponseEntity.ok(employees);
    }
    @PostMapping("/add")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
    	 employee.setId(sequenceGeneratorService.generateSequence("employee_sequence"));
        Employee savedEmployee = empService.save(employee);
        return ResponseEntity.ok(savedEmployee);
    }
       
    @GetMapping("/recent")
    public ResponseEntity<List<Employee>> getRecentEmployees() {
        List<Employee> recentEmployees = empRepo.findTop5ByOrderByCreatedAtDesc();
        return ResponseEntity.ok(recentEmployees);
    }

    // PUT update employee
    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee newEmpData) {
        return empService.findById(id)
                .map(employee -> {
                    employee.setFname(newEmpData.getFname());
                    employee.setLname(newEmpData.getLname());
                    employee.setEmail(newEmpData.getEmail());
                    employee.setMobile(newEmpData.getMobile());
                    employee.setGender(newEmpData.getGender());
                    employee.setPassword(newEmpData.getPassword());
                    Employee updatedEmp = empService.save(employee);
                    return ResponseEntity.ok(updatedEmp);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
    	empService.delete(id);
    }
}
