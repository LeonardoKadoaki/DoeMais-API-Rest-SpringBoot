package com.doemais.api.dto;

import javax.validation.constraints.NotEmpty;

public class ResetarSenhaDto {
	
	@NotEmpty
	private String password;

	@NotEmpty
	private String confirmPassword;

	@NotEmpty
	private String token;

	public ResetarSenhaDto(@NotEmpty String password, @NotEmpty String confirmPassword, @NotEmpty String token) {
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.token = token;
	}
	
	public ResetarSenhaDto() {
		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
        this.token = token;
    }
}
