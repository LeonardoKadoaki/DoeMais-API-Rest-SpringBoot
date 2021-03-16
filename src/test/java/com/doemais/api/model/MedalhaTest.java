package com.doemais.api.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.doemais.api.models.Medalha;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MedalhaTest {
   
	private Medalha medalha = new Medalha();
	
	@Test
     public void TestDescricaoMedalha() {
		
		medalha.setDescricaoMedalha("Teste");
		
		assertEquals("Teste", medalha.getDescricaoMedalha());
		
	}
	
	
	@Test
	public void TestEquals() {
		Medalha medalha2 = new Medalha();
		
		medalha.setDescricaoMedalha("teste");
		medalha.setNomeMedalha("testando");
		
		medalha2.setDescricaoMedalha("teste");
		medalha2.setNomeMedalha("testando");
		
		boolean res = medalha.equals(medalha2);
		
		assertTrue(res);
	}
	
	@Test
	public void TestNotEquals() {
		Medalha medalha2 = new Medalha();
		
		medalha.setDescricaoMedalha("teste");
		medalha.setNomeMedalha("testando");
		
		medalha2.setDescricaoMedalha("teste");
		medalha2.setNomeMedalha("testando2");
		
		boolean res = medalha.equals(medalha2);
		
		assertTrue(res);
	}
	
	@Test
	public void TestHashCode() {
		Medalha medalha2 = new Medalha();
		
		medalha.setDescricaoMedalha("teste");
		medalha.setNomeMedalha("testando");
		
		medalha2.setDescricaoMedalha("teste");
		medalha2.setNomeMedalha("testando");
		
		assertEquals(medalha.hashCode(), medalha2.hashCode());
	}
	
	
	
	
}
