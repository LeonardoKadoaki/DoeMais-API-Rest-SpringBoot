package com.doemais.api.models;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




@SpringBootTest
 class MissaoTest {

	@Test
	 void TestEquals() {
		Missao missao = new Missao();
		Missao missao2 = new Missao();

		missao.setDescricaoMissao("Atingir primeira doação");
		missao.setMoedasMissao(100);
		
		missao2.setDescricaoMissao("Atingir primeira doação");
		missao2.setMoedasMissao(100);
		
		boolean res = missao.equals(missao2);
		
		assertTrue(res);
		
	}
	
	@Test
	 void TestNotEquals() {
		Missao missao = new Missao();
		Missao missao2 = new Missao();

		missao.setDescricaoMissao("Atingir primeira doação");
		missao.setMoedasMissao(100);
		
		missao2.setDescricaoMissao("Conseguir primeira doação");
		missao2.setMoedasMissao(50);
		
		boolean res = missao.equals(missao2);
		
		assertTrue(res);
		
	}
	
	@Test
	 void TestDescricaoMissao() {
		Missao missao = new Missao();
		
		missao.setDescricaoMissao("Missao Teste");

	    assertEquals("Missao Teste", missao.getDescricaoMissao());
		
	}
	
	@Test
	void TestHashCode() {
		Missao missao = new Missao();
		missao.setDescricaoMissao("teste");
		missao.setIdMissao(1);
		missao.setMoedasMissao(300);
		
		Missao missao2 = new Missao();
		missao2.setDescricaoMissao("teste");
		missao2.setIdMissao(1);
		missao2.setMoedasMissao(300);
		assertEquals(missao.hashCode(), missao2.hashCode());
	}
	
	@Test
	void TestToString() {
		Missao missao = new Missao();
		missao.setDescricaoMissao("teste");
		missao.setIdMissao(1);
		missao.setMoedasMissao(300);
		
		assertNotNull(missao.toString());
	}
	
	
}
