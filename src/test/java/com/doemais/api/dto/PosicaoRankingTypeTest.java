package com.doemais.api.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PosicaoRankingTypeTest {

	private PosicaoRankingType posicao;
	private PosicaoRankingType posicao2;

	@Test
	void TestSettersAndGetters() {
		posicao = new PosicaoRankingType();
		
		posicao.setNome("teste");
		posicao.setPosicao(1);
		posicao.setTotalMoedas(1);
		
		assertEquals("teste", posicao.getNome());
		assertEquals(1, posicao.getPosicao());
		assertEquals(1, posicao.getTotalMoedas());


	}
	

	@Test
	void TestConstrutor() {
		posicao = new PosicaoRankingType(1,"teste", 1);
		posicao2 = new PosicaoRankingType(1,"teste", 1);

		assertNotEquals(posicao, posicao2);


	}
	
}
