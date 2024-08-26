package com.application.dietfood.Dto;

public class ResponseUser {
	
	private String username;
	private String status;
	
	
	// Constructors
    public ResponseUser() {}

    public ResponseUser(String username, String status) {
        this.username = username;
        this.status = status;
    }
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
