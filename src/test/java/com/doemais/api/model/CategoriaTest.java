package com.doemais.api.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.doemais.api.models.Categoria;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoriaTest {

	
	@Test
	public void TestEquals() {
		Categoria categoria = new Categoria();
		Categoria categoria2 = new Categoria();
		
		categoria.setNome("teste");
		categoria2.setNome("teste");
		
		boolean res = categoria.equals(categoria2);
		
		assertTrue(res);
		
	}
	
	@Test
	public void TestNotEquals() {
		Categoria categoria = new Categoria();
		Categoria categoria2 = new Categoria();
		
		categoria.setNome("teste");
		categoria2.setNome("teste2");
		
		boolean res = categoria.equals(categoria2);
		
		assertTrue(res);
		
	}
	
}
