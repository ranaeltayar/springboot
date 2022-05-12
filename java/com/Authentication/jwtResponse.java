package com.Authentication;

public class jwtResponse {
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public jwtResponse(String token) {
		super();
		this.token = token;
	}
	

}
