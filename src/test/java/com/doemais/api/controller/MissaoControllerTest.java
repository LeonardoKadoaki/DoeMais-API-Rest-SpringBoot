package com.doemais.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.doemais.api.models.Missao;
import com.doemais.api.services.MissaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
class MissaoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private MissaoService missaoService;
	
	private Missao missao;

	private List<Missao> missoes = new ArrayList<>();

	@BeforeEach
	void Configuracao() {
		missao = new Missao(2, "Teste", 300);
	}

	@Test
	void CadastraMissaoTest() throws JsonProcessingException, Exception {

		Mockito.when(missaoService.cadastrarMissao(missao)).thenReturn(missao);

		mockMvc.perform(
				post("/api/missao").contentType("application/json").content(objectMapper.writeValueAsString(missao)))
				.andExpect(status().isOk());
	}

	@Test
	void ListaMissoesTest() throws JsonProcessingException, Exception {
      Mockito.when(missaoService.consultarMissoes()).thenReturn(missoes);
		
		mockMvc.perform(get("/api/missao").contentType("application/json")).andExpect(status().isOk());
	}

}