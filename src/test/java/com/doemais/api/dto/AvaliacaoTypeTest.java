package com.doemais.api.dto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




@SpringBootTest
 class AvaliacaoTypeTest {

	@Test
	 void TestSettersAndGetters() {
	AvaliacaoType avaliacao = new AvaliacaoType();
	
		avaliacao.setAvaliacao(2);
		
		assertEquals(2, avaliacao.getAvaliacao());
				
	}
	
	@Test
	 void TestandoEquals() {
		AvaliacaoType avaliacao = new AvaliacaoType();
		AvaliacaoType avaliacao2 = new AvaliacaoType();
		
		avaliacao.setAvaliacao(2);
		
		avaliacao2.setAvaliacao(2);
		
		
		boolean res =avaliacao.equals(avaliacao2);
		
		assertFalse(res);
	
	}
	
	@Test
	 void TestandoNotEquals() {
		AvaliacaoType avaliacao = new AvaliacaoType();
		AvaliacaoType avaliacao2 = new AvaliacaoType();
		
		avaliacao.setAvaliacao(2);
		
		avaliacao2.setAvaliacao(4);
		
		
		boolean res = avaliacao.equals(avaliacao2);
		
		assertFalse(res);
		
	}
	
	@Test
	 void TestHashCode() {
		AvaliacaoType avaliacao = new AvaliacaoType();
		AvaliacaoType avaliacao2 = new AvaliacaoType();
		
		avaliacao.setAvaliacao(2);
		
		avaliacao2.setAvaliacao(2);
		
	
		assertNotEquals(avaliacao.hashCode(), avaliacao2.hashCode());
		
	}
	


	
}
