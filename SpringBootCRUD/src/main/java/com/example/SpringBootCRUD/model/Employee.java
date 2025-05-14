package com.example.SpringBootCRUD.model;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "employees")  // MongoDB collection name
public class Employee {

    @Id
    public long id = 1;  // MongoDB uses String/ObjectId for IDs

    private String fname;
    private String lname;
    private String mobile;
    private String gender;
    private String email;
    private String password;
    private String department;
    public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Field("createdAt")
    private LocalDateTime createdAt;

    // Constructors
    public Employee() {}

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

	public Employee(long id,String fname, String lname, String email, String mobile, String gender, String password, LocalDateTime createdAt, String department) {
        this.id = id;
    	this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.password = password;
        this.createdAt = createdAt;
        this.department = department;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
