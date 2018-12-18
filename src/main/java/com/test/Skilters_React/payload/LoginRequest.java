package com.test.Skilters_React.payload;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	
	@NotBlank(message = "Vous devez inserer un email")
	private String email;
	
	@NotBlank(message = "Vous devez inserer un mot de passe")
	private String password;
	
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
