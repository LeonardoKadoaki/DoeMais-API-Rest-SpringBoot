package com.doemais.api.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.doemais.api.models.Medalha;


@SpringBootTest
 class MedalhaTest {
   
	private Medalha medalha = new Medalha();
	
	@Test
     void TestDescricaoMedalha() {
		
		medalha.setDescricaoMedalha("Teste");
		
		assertEquals("Teste", medalha.getDescricaoMedalha());
		
	}
	
	
	@Test
	 void TestEquals() {
		Medalha medalha2 = new Medalha();
		
		medalha.setDescricaoMedalha("teste");
		medalha.setNomeMedalha("testando");
		
		medalha2.setDescricaoMedalha("teste");
		medalha2.setNomeMedalha("testando");
		
		boolean res = medalha.equals(medalha2);
		
		assertTrue(res);
	}
	
	@Test
	 void TestNotEquals() {
		Medalha medalha2 = new Medalha();
		
		medalha.setDescricaoMedalha("teste");
		medalha.setNomeMedalha("testando");
		
		medalha2.setDescricaoMedalha("teste");
		medalha2.setNomeMedalha("testando2");
		
		boolean res = medalha.equals(medalha2);
		
		assertTrue(res);
	}
	
	@Test
	void TestHashCode() {
		Medalha medalha2 = new Medalha();
		
		medalha.setDescricaoMedalha("teste");
		medalha.setNomeMedalha("testando");
		
		medalha2.setDescricaoMedalha("teste");
		medalha2.setNomeMedalha("testando");
		
		assertEquals(medalha.hashCode(), medalha2.hashCode());
	}
	
	
	@Test
	void TestConstrutor() {
		Medalha medalha = new Medalha(1, "teste", "teste");
		assertEquals(1, medalha.getIdMedalha());
		assertEquals("teste", medalha.getDescricaoMedalha());
		assertEquals("teste", medalha.getNomeMedalha());

	}
	
	@Test
	void TestToString() {
		Medalha medalha = new Medalha(1, "teste", "teste");
       assertNotNull(medalha.toString());
	}
	
}
