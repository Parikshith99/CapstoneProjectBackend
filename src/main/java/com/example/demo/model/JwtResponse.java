package com.example.demo.model;

public class JwtResponse {
	boolean Success;
	String token;

	public JwtResponse(boolean success, String token) {
		super();
		Success = success;
		this.token = token;
	}

	public boolean isSuccess() {
		return Success;
	}

	public void setSuccess(boolean success) {
		Success = success;
	}

	public JwtResponse() {
		super();
	}

	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
