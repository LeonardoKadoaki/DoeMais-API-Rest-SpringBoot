package com.doemais.api.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;
import com.doemais.api.forms.LoginForm;
import com.doemais.api.security.dto.TokenDto;
import com.doemais.api.security.services.TokenService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthServiceTest {


	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	
	@Test
	public void TestAuthUsuario() throws Exception {
		
		LoginForm login = new LoginForm();
		
		login.setEmail("guilherme@gmail.com");
		login.setSenha("123456789");
		
        UsernamePasswordAuthenticationToken tokenTest = login.converter();
		
		
			Authentication auth = authManager.authenticate(tokenTest);
			String token = tokenService.gerarToken(auth);
			ResponseEntity.ok(new TokenDto(token, "Bearer"));	
			ResponseEntity.badRequest().build();
		}
	
	
	/*@Test
	public void TestFailedAuthUsuario() throws Exception{
		LoginForm login = new LoginForm();
		
		login.setEmail("teste@gmail.com");
		login.setSenha("teste");
		
		 UsernamePasswordAuthenticationToken tokenTest = login.converter();
			Authentication auth = authManager.authenticate(tokenTest);
			String token = tokenService.gerarToken(auth);
			
			ResponseEntity.ok(new TokenDto(token, "Bearer"));
			ResponseEntity.badRequest().build();
			
		
	}*/
	
		
	}
		

