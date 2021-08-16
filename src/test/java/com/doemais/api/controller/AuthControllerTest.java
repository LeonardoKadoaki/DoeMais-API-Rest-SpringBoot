package com.doemais.api.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.doemais.api.dto.CadastroDto;
import com.doemais.api.forms.LoginForm;
import com.doemais.api.services.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private AuthService authService;
	
	private LoginForm loginForm = new LoginForm();
	private CadastroDto cadastroDto = new CadastroDto();
	

	@BeforeEach
	void Configuracao() {
		cadastroDto = new CadastroDto();
		cadastroDto.setCpf("mikeias testes");
		cadastroDto.setDataNascimento(LocalDate.of(1998, Month.DECEMBER, 22));
		cadastroDto.setEmail("mikeias@gmail.com");
		cadastroDto.setGenero("M");
		cadastroDto.setNome("Junit testes");
		cadastroDto.setNumeroCelular("1154572457");
		cadastroDto.setSenha("testes Junit");
		cadastroDto.setSobre("testando o Junit");
		cadastroDto.setUserName("Junit testes");
	loginForm = new LoginForm(cadastroDto.getEmail(), cadastroDto.getSenha());
	}

	@Test
	void LoginTest() throws Exception {

		Mockito.when(authService.autenticaUsuario(loginForm)).thenCallRealMethod();

		mockMvc.perform(
				post("/auth").contentType("application/json").content(objectMapper.writeValueAsString(loginForm)))
				.andExpect(status().isOk());

	}


}
