package com.doemais.api.dto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class AvaliacaoAnuncioDtoTest {

	@Test
	public void TestSettersAndGetters() {
	AvaliacaoDto anuncio = new AvaliacaoDto();
	
		anuncio.setNotaAvaliacao(1);
		
		assertEquals(1, anuncio.getNotaAvaliacao());
				
	}
	
	@Test
	public void TestandoEquals() {
		AvaliacaoDto anuncio = new AvaliacaoDto();
		AvaliacaoDto anuncio2 = new AvaliacaoDto();
		
		
		anuncio.setNotaAvaliacao(4);
		
		anuncio2.setNotaAvaliacao(4);
		
		
		boolean res = anuncio.equals(anuncio2);
		
		assertTrue(res);
	
	}
	
	@Test
	public void TestandoNotEquals() {
		AvaliacaoDto anuncio = new AvaliacaoDto();
		AvaliacaoDto anuncio2 = new AvaliacaoDto();
		
		
		anuncio.setNotaAvaliacao(4);
		
		anuncio2.setNotaAvaliacao(3);
		
		boolean res = anuncio.equals(anuncio2);
		
		assertFalse(res);
		
	}
	
	@Test
	public void TestHashCode() {
		AvaliacaoDto anuncio = new AvaliacaoDto();
		AvaliacaoDto anuncio2 = new AvaliacaoDto();
		
		
		anuncio.setNotaAvaliacao(4);
		
		anuncio2.setNotaAvaliacao(4);
		
		assertEquals(anuncio.hashCode(), anuncio2.hashCode());
		
	}
	


	
}
