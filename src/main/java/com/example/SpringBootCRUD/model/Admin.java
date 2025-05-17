package com.example.SpringBootCRUD.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")  
public class Admin {
	
	@Id
	private int id = 1;
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String email;
    private String password;
    private String oldPassword;
    private String newPassword;

	public String getNewPassword(){
		return newPassword;
	}
	public void setNewPassword(String newPassword){
		this.newPassword = newPassword;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
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
