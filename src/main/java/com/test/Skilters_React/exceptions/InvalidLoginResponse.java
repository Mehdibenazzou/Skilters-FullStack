package com.test.Skilters_React.exceptions;

public class InvalidLoginResponse {
	private String email;
	private String password;
	
	public InvalidLoginResponse() {
		this.email = "Email invalide";
		this.password = "Mot de passe invalide";
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
