package com.doemais.api.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.doemais.api.dto.EmailAddressType;
import com.doemais.api.dto.IdUsuarioType;
import com.doemais.api.models.Email;
import com.doemais.api.models.Usuario;
import com.doemais.api.services.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class EmailControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private EmailService emailService;
	
	private Email email = new Email();
	private Usuario usuario = new Usuario();
	private Usuario usuario2 = new Usuario();
	private List<Email> emails = new ArrayList<>();
	private EmailAddressType emailtype = new EmailAddressType();
	private IdUsuarioType idUsuarioType = new IdUsuarioType();
	
	@BeforeEach
	void Configuracao()  {
		
		email = new Email();
		email.setIdEmail(1);
		email.setAssunto("testando");
		email.setCorpoEmail("testestdds");
		email.setDataEnvioEmail(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		usuario = new Usuario();
		email.setUsuarioDest(usuario);
		usuario2 = new Usuario();
		email.setUsuarioRemet(usuario2);
		
		emailtype = new EmailAddressType();
		emailtype.setEmail("santos.g@aluno.ifsp.edu.br");
		
	}

	
	
	@Test
	void BuscarTodosEmailsTest() throws JsonProcessingException, Exception {
		Mockito.when(emailService.listarEmails()).thenReturn(emails);
		
		mockMvc.perform(get("/api/email")
				   .contentType("application/json"))
			       .andExpect(status().isOk());
		
	}
	
	@Test
	void ObtemIdDoUsuarioTest() throws JsonProcessingException, Exception {
		Mockito.when(emailService.consultarIdUsuario(emailtype)).thenReturn(idUsuarioType);
		
		mockMvc.perform(get("/api/email/usuario")
				   .contentType("application/json")
				   .content(objectMapper.writeValueAsString(emailtype)))
			       .andExpect(status().isOk());
		
	}
	
	@Test
	void EnviaEmailTest() throws JsonProcessingException, Exception {
        Mockito.when(emailService.enviarEmail(email)).thenReturn(email);
		
		mockMvc.perform(post("/api/email")
			   .contentType("application/json")
			   .content(objectMapper.writeValueAsString(email)))
		       .andExpect(status().isOk());
		
	}
	
	@Test
	void BuscarEmailTest() throws JsonProcessingException, Exception {
		Mockito.when(emailService.consultarEmail(email.getIdEmail())).thenReturn(email);
		
		mockMvc.perform(get("/api/email/" + email.getIdEmail())
				   .contentType("application/json"))
			       .andExpect(status().isOk());
		
	}
	
	
	@Test
	void ListaEmailsEnviadosTest() throws JsonProcessingException, Exception {
		Mockito.when(emailService.listarEmailsEnviados(usuario.getIdUsuario())).thenReturn(emails);
		
		mockMvc.perform(get("/api/email/usuario/" +  usuario.getIdUsuario() + "/emails-enviados")
				   .contentType("application/json"))
			       .andExpect(status().isOk());
		
	}
	
	@Test
	void ListaEmailsRecebidosTest() throws JsonProcessingException, Exception {
		Mockito.when(emailService.listarEmailsRecebidos(usuario.getIdUsuario())).thenReturn(emails);

		
		mockMvc.perform(get("/api/email/usuario/" +  usuario.getIdUsuario() + "/emails-recebidos")
				   .contentType("application/json"))
			       .andExpect(status().isOk());
		
	}
	
	@Test
	void ObtemEnderecoEmailTest() throws JsonProcessingException, Exception {
		Mockito.when(emailService.consultarEnderecoEmail(usuario.getIdUsuario())).thenReturn(emailtype);

		
		mockMvc.perform(get("/api/email/usuario/" +  usuario.getIdUsuario() + "/endereco-email")
				   .contentType("application/json"))
			       .andExpect(status().isOk());
		
	}
	
	
	
	
	

}
