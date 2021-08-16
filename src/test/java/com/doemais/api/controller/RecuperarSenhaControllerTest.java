package com.doemais.api.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.doemais.api.dto.RecuperarSenhaDto;
import com.doemais.api.dto.ResetarSenhaDto;
import com.doemais.api.models.Auth;
import com.doemais.api.models.ResetarSenhaToken;
import com.doemais.api.services.RecuperarSenhaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class RecuperarSenhaControllerTest {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RecuperarSenhaService recuperarSenhaService;

	private RecuperarSenhaDto forgotDto = new RecuperarSenhaDto();

	private ResetarSenhaDto resetDto = new ResetarSenhaDto();

	private ResetarSenhaToken resetToken = new ResetarSenhaToken();
	
	private Map<String, String> maps;

	private RecuperarSenhaDto rec = new RecuperarSenhaDto();

	@BeforeEach
	void Configuracao() throws ParseException   {
        
    
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date datas = formato.parse("27/06/2021");
		
		
		resetToken = new ResetarSenhaToken();
		resetToken.setId(1L);
		resetToken.setToken(UUID.randomUUID().toString());
		resetToken.setExpiryDate(30);
		resetToken.setExpiryDate(datas);
		rec = new RecuperarSenhaDto();
		rec.setEmail("santos.g@aluno.ifsp.edu.br");
		forgotDto = new RecuperarSenhaDto();
		forgotDto.setEmail("santos.g@aluno.ifsp.edu.br");
		
		resetDto = new ResetarSenhaDto();
		resetDto.setToken(resetToken.getToken());
		resetDto.setPassword("12345");
		resetDto.setConfirmPassword("12345");
	}

	@Test
	void recuperaEmailResetaSenhaTest() throws JsonProcessingException, Exception  {
     Mockito.when(recuperarSenhaService.enviaEmailRecuperaSenha(forgotDto)).thenReturn(rec);
     
		mockMvc.perform(post("/api/recuperar-senha/recupera-email")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(forgotDto)))
				.andExpect(status().isOk());
	}
	
	@Test
	void getResetSenhaTest() throws JsonProcessingException, Exception {
		Mockito.when(recuperarSenhaService.retornaPaginaRedefineSenha(resetToken.getToken())).thenReturn(resetToken);
		

		mockMvc.perform(
				get("/api/recuperar-senha/redefinir-senha").contentType("application/json").content(objectMapper.writeValueAsString(resetToken)))
				.andExpect(status().isOk());

	}
	
	@Test
	void redefineSenhaTest() throws JsonProcessingException, Exception {
		
	    Mockito.when(recuperarSenhaService.RedefineSenha(resetDto)).thenReturn(resetDto);
	    
	    mockMvc.perform(
				post("/api/recuperar-senha/redefinir-senha").contentType("application/json").content(objectMapper.writeValueAsString(resetDto)))
				.andExpect(status().isOk());
	    
	    
	}
	

}
