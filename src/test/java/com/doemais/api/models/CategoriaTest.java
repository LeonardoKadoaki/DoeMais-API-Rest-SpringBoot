package com.doemais.api.models;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;




@SpringBootTest
 class CategoriaTest {

	
	@Test
	 void TestEquals() {
		Categoria categoria = new Categoria();
		Categoria categoria2 = new Categoria();
		
		categoria.setNome("teste");
		categoria2.setNome("teste");
		
		boolean res = categoria.equals(categoria2);
		
		assertTrue(res);
		
	}
	
	@Test
	void TestNotEquals() {
		Categoria categoria = new Categoria();
		Categoria categoria2 = new Categoria();
		
		categoria.setNome("teste");
		categoria2.setNome("teste2");
		
		boolean res = categoria.equals(categoria2);
		
		assertTrue(res);
		
	}
	
	@Test
	void TestHashCode() {
		Categoria categoria = new Categoria();
		Categoria categoria2 = new Categoria();
		
		categoria.setNome("teste");
		categoria2.setNome("teste");
		
		assertEquals(categoria.hashCode(), categoria2.hashCode());
	}
	
}
