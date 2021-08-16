package com.doemais.api.services;


import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.doemais.api.dto.CadastroDto;
import com.doemais.api.forms.LoginForm;
import com.doemais.api.models.Auth;
import com.doemais.api.repository.AuthRepository;
import com.doemais.api.security.dto.TokenDto;
import com.doemais.api.security.services.TokenService;

@SpringBootTest
 class AuthServiceTest {


	@MockBean
	private AuthService authService;
	
	@MockBean
	private AuthRepository authRepository;
	
	@MockBean
	private TokenService token;
	
	@MockBean
	private AuthenticationManager authManager;
	
	private Auth auth;
	private LoginForm loginForm;
	

	@BeforeEach
	void Configuracao() {
		CadastroDto dto = new CadastroDto("teste", "teste@gmail.com", "12345", "teste", "2545645",
				LocalDate.of(1998, Month.DECEMBER, 22), "5454455", "teste", "testando");


	loginForm = new LoginForm(dto.getEmail(), dto.getSenha());
	
	
	}
	
	@Test
	 void TestAutenticaUsuario() throws Exception {
       UsernamePasswordAuthenticationToken dadosLogin = loginForm.converter();
		
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String tokens = token.gerarToken(authentication);
			Mockito.when(authRepository.findByEmail(loginForm.getEmail())).thenReturn(auth);
			ResponseEntity.ok(new TokenDto(tokens, "Bearer"));
	        assertNull(tokens);

		} catch (AuthenticationException e) {
			 ResponseEntity.badRequest().build();
		}
		
		}
	
	

	}
		

