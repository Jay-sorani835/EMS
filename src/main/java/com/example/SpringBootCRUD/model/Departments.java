package com.example.SpringBootCRUD.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "departments")
public class Departments {
    @Id
    private String id;
    private String name;

    public Departments() {}
    // Constructor, Getters, and Setters
    public Departments(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Departments(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

