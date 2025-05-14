package com.example.SpringBootCRUD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.SpringBootCRUD.Repo.EmployeeRepo;
import com.example.SpringBootCRUD.UserService.EmployeeService;
import com.example.SpringBootCRUD.UserService.SequenceGeneratorService;
import com.example.SpringBootCRUD.UserService.UserService;
import com.example.SpringBootCRUD.model.ChangePassword;
import com.example.SpringBootCRUD.model.Employee;
import com.example.SpringBootCRUD.model.Admin;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {
	@Autowired
	private EmployeeRepo empRepo;
	private UserService userService;
	

    private final SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private EmployeeService service;
    public EmployeeController(SequenceGeneratorService sequenceGeneratorService, UserService userService  ) {
        this.userService = userService;
		this.sequenceGeneratorService = sequenceGeneratorService;
    }
    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return service.getAll();
    }
    
    @PostMapping("/add")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
    	 employee.setId(sequenceGeneratorService.generateSequence("employee_sequence"));
        Employee savedEmployee = service.save(employee);
        return ResponseEntity.ok(savedEmployee);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin) {
        System.out.println("Login request received for: " + admin.getEmail());

        Optional<Admin> userOpt = empRepo.findByEmail(admin.getEmail());

        if (userOpt.isPresent()) {
            Admin user = userOpt.get();

            if (user.getPassword().equals(admin.getPassword())) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(401).body("Incorrect password");
            }
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }
    
    @GetMapping("/recent")
    public ResponseEntity<List<Employee>> getRecentEmployees() {
        List<Employee> recentEmployees = empRepo.findTop5ByOrderByCreatedAtDesc();
        return ResponseEntity.ok(recentEmployees);
    }

    // PUT update employee
    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee newEmpData) {
        return service.findById(id)
                .map(employee -> {
                    employee.setFname(newEmpData.getFname());
                    employee.setLname(newEmpData.getLname());
                    employee.setEmail(newEmpData.getEmail());
                    employee.setMobile(newEmpData.getMobile());
                    employee.setGender(newEmpData.getGender());
                    employee.setPassword(newEmpData.getPassword());
                    Employee updatedEmp = service.save(employee);
                    return ResponseEntity.ok(updatedEmp);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // Clears session on server
        return ResponseEntity.ok("Logout successful");
    }
    
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword request) {

        if (request.getOldPassword() == null || request.getOldPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("{\"message\":\"Old password is required.\"}");
        }

        if (request.getNewPassword() == null || request.getNewPassword().isEmpty() ||
            request.getConfirmPassword() == null || request.getConfirmPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("{\"message\":\"New and Confirm passwords are required.\"}");
        }

        String result = userService.changePassword(
                request.getOldPassword(),
                request.getNewPassword(),
                request.getConfirmPassword(),
                1,
                request.getEmail()
        );

        if (result.equals("Password changed successfully")) {
            return ResponseEntity.ok("{\"message\":\"" + result + "\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"message\":\"" + result + "\"}");
        }
    }

    
    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser(HttpSession session) {
        Employee user = (Employee) session.getAttribute("user");
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No active session");
        }
    }
    
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        service.delete(id);
    }
}
