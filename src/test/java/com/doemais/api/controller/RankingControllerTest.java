package com.doemais.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.doemais.api.dto.PosicaoRankingType;
import com.doemais.api.services.RankingService;

@SpringBootTest
@AutoConfigureMockMvc
class RankingControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RankingService rankingService;
	
	private PosicaoRankingType posicaoRankingType;
	
	private List<PosicaoRankingType> posicoes = new ArrayList<>();
	
	@BeforeEach
	void Configuracao() {
		posicaoRankingType  = new PosicaoRankingType(1, "Teste", 300);
		
	}
	
	@Test
	void ConsultarPosicaoRankingTest() throws Exception {
	Mockito.when(rankingService.consultarRanking()).thenReturn(posicoes);
		
	mockMvc.perform(get("/api/ranking")
			.contentType("application/json"))
	         .andExpect(status().isOk());
	
	}
	
	
	
}
