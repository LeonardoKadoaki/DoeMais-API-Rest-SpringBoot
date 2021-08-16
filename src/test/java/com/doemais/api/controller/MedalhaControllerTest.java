package com.doemais.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.doemais.api.models.Medalha;
import com.doemais.api.services.MedalhaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class MedalhaControllerTest {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MedalhaService medalhaService;
	
	private Medalha medalha;
	
	private List<Medalha> medalhas = new ArrayList<>();
	
	
	@BeforeEach
	void Configuracao() {
		medalha = new Medalha();
		
		medalha.setIdMedalha(1);
        medalha.setDescricaoMedalha("teste");
        medalha.setNomeMedalha("teste");
	}
	
	@Test
	void cadastrarMedalhaTest() throws JsonProcessingException, Exception {
		Mockito.when(medalhaService.cadastrarMedalha(medalha)).thenReturn(medalha);
		
		mockMvc.perform(post("/api/medalha")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(medalha)))
		        .andExpect(status().isOk());
	}
	
	@Test
	void consultarMedalhasTest() throws Exception {
		Mockito.when(medalhaService.consultarMedalhas()).thenReturn(medalhas);
		
		mockMvc.perform(get("/api/medalha")
				.contentType("application/json"))
		        .andExpect(status().isOk());
	}
	
	
	
	
}
