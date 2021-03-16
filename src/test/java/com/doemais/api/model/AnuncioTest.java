package com.doemais.api.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.doemais.api.models.Anuncio;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnuncioTest {

	@Test
	public void TestEquals() {
		Anuncio anuncio = new Anuncio();
		Anuncio anuncio2 = new Anuncio();
		
		anuncio.setTitulo("Teste");
		anuncio.setDescricao("Livro");
		
		anuncio2.setTitulo("Teste");
		anuncio2.setDescricao("Livro");
		
		boolean res = anuncio.equals(anuncio2);
		
		assertTrue(res);
		
	}
	
	@Test
	public void TestNotEquals() {
		Anuncio anuncio = new Anuncio();
		Anuncio anuncio2 = new Anuncio();
		
		anuncio.setTitulo("Teste");
		anuncio.setDescricao("Livro");
		
		anuncio2.setTitulo("Teste2");
		anuncio2.setDescricao("Livro");
		
		boolean res = anuncio.equals(anuncio2);
		
		assertTrue(res);
		
	}
	
	@Test
	public void TestTituloAnuncio() {
		Anuncio anuncio = new Anuncio();
		
		
		anuncio.setTitulo("Teste");

	assertEquals("Teste", anuncio.getTitulo());
		
	}
	
	
}
