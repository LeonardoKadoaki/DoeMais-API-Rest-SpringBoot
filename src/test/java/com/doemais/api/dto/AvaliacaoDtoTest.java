package com.doemais.api.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AvaliacaoDtoTest {
	
    private AvaliacaoDto avaliacaoDto;
    private AvaliacaoDto avaliacaoDto2;
	
	@Test
	void TestGettersAndSetters() throws IllegalArgumentException {
		avaliacaoDto = new AvaliacaoDto();
		
		avaliacaoDto.setIdAnuncio(1);
		avaliacaoDto.setIdAvaliador(1);
		avaliacaoDto.setNotaAvaliacao(4);
		
		assertEquals(1, avaliacaoDto.getIdAnuncio());
		assertEquals(1, avaliacaoDto.getIdAvaliador());
		

	}

	
	@Test
	void TestEquals() {
		avaliacaoDto = new AvaliacaoDto();
		avaliacaoDto2 = new AvaliacaoDto();


		avaliacaoDto.setIdAnuncio(1);
		avaliacaoDto.setIdAvaliador(1);
		avaliacaoDto.setNotaAvaliacao(4);
		
		avaliacaoDto2.setIdAnuncio(1);
		avaliacaoDto2.setIdAvaliador(1);
		avaliacaoDto2.setNotaAvaliacao(4);
		
		boolean res = avaliacaoDto.equals(avaliacaoDto2);

		assertNotEquals(res, avaliacaoDto.getIdAnuncio());
		assertNotEquals(res, avaliacaoDto.getIdAvaliador());
		assertNotEquals(res, avaliacaoDto.getNotaAvaliacao());



	}

	
	@Test
	void TestNotEquals() {
		avaliacaoDto = new AvaliacaoDto();
		avaliacaoDto2 = new AvaliacaoDto();


		avaliacaoDto.setIdAnuncio(1);
		avaliacaoDto.setIdAvaliador(1);
		avaliacaoDto.setNotaAvaliacao(4);
		
		avaliacaoDto2.setIdAnuncio(2);
		avaliacaoDto2.setIdAvaliador(2);
		avaliacaoDto2.setNotaAvaliacao(5);
		
		boolean res = avaliacaoDto.equals(avaliacaoDto2);

		assertFalse(res);

	}
	
	@Test
	void TestHashCode() {
		avaliacaoDto = new AvaliacaoDto();
		avaliacaoDto2 = new AvaliacaoDto();


		avaliacaoDto.setIdAnuncio(1);
		avaliacaoDto.setIdAvaliador(1);
		avaliacaoDto.setNotaAvaliacao(4);
		
		avaliacaoDto2.setIdAnuncio(1);
		avaliacaoDto2.setIdAvaliador(1);
		avaliacaoDto2.setNotaAvaliacao(4);
		
		

		assertEquals(avaliacaoDto.hashCode(), avaliacaoDto2.hashCode());

	}
	
	
	
}
