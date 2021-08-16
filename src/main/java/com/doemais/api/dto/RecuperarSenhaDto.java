package com.doemais.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class RecuperarSenhaDto {

	@Email
    @NotEmpty
    private String email;

    public RecuperarSenhaDto(@Email @NotEmpty String email) {
		this.email = email;
	}
    
    public RecuperarSenhaDto() {
    	
    }

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
