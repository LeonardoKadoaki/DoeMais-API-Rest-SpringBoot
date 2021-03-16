package com.doemais.api.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.doemais.api.dto.CadastroDto;
import com.doemais.api.forms.LoginForm;
import com.doemais.api.models.Auth;
import com.doemais.api.models.Perfil;
import com.doemais.api.models.Usuario;
import com.doemais.api.security.services.TokenService;
import com.doemais.api.services.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private AuthService authService;
	
	@BeforeEach
	public void setup() {	
	CadastroDto cadastroDto = new CadastroDto();
	Usuario usuario = new Usuario();
	Perfil perfil = new Perfil();
	

	Auth auth = new Auth(usuario, "santos.g@aluno.ifsp.edu.br", "12345");
	
	auth.getUsuario();
	auth.getEmail();
	auth.getSenha();
	
	perfil.setId(1L);
	perfil.setNome(cadastroDto.getUserName());
	
	usuario.setUserName(cadastroDto.getUserName());
	usuario.setIdUsuario(3);
	
	perfil.setId(1L);
	
		
	}
	
	@Test
	public void TestAuth() throws Exception{
	
	 LoginForm login = new LoginForm();
	 login.setEmail("gui@gmail.com");
	 login.setSenha("123");
	 
	 mockMvc.perform(post("/auth")
			 .contentType("application/json")
			 .content(objectMapper.writeValueAsString(login)));
	 
	 authService.autenticaUsuario(login);
	 	
	}
	

}
