package com.doemais.api.forms;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@SpringBootTest
class LoginFormTest {

	private LoginForm form;
	
	@Test
	void GetterAndSetterEmailTest() {
		 form = new LoginForm();
		
		form.setEmail("santos.g@aluno.ifsp.edu.br");
		
		assertEquals("santos.g@aluno.ifsp.edu.br", form.getEmail());
	}
	
	@Test
	void GetterAndSetterSenhaTest() {
		 form = new LoginForm();
		
		form.setSenha("12345");
		
		assertEquals("12345", form.getSenha());
	}
	
	
	@Test
	public ObjectAssert<UsernamePasswordAuthenticationToken> converter() {
		 form = new LoginForm();
		 
		  return assertThat(new UsernamePasswordAuthenticationToken(form.getEmail(), form.getSenha()));
		
		 
         
	}
	
}
