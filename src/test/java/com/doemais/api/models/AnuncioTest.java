package com.doemais.api.models;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.doemais.api.exception.ConflictException;




@SpringBootTest
 class AnuncioTest {

	@Test
	 void TestEquals() {
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
	 void TestNotEquals() {
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
	 void TestTituloAnuncio() {
		Anuncio anuncio = new Anuncio();
		
		
		anuncio.setTitulo("Teste");

	assertEquals("Teste", anuncio.getTitulo());
		
	}
	
	@Test
	void TestConstrutor() {
		
		Anuncio anuncio = new Anuncio(1, 1, "teste", "teste", 2.0);
		Anuncio anuncio2 = new Anuncio(1, 1, "teste", "teste", 2.0);
		
		assertEquals(anuncio, anuncio2);

	}
	
	@Test
	void TestHashCode() throws ConflictException {
		Anuncio anuncio = new Anuncio();
		Anuncio anuncio2 = new Anuncio();
		Categoria cat = new Categoria();
		StatusAnuncio stat = new StatusAnuncio();
		Usuario user = new Usuario();
		List<AnuncioFotos> fotos = new ArrayList<>();
		
		anuncio.setCategoria(cat);
		anuncio.setCategoriaUsuarioAnunciante(user.getCategoria());
		anuncio.setDataCriacao(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		anuncio.setDataExpiracao(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		anuncio.setDataFim(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		anuncio.setDescricao("teste");
		anuncio.setFotos(fotos);
		anuncio.setIdAnuncio(1);
		anuncio.setIdAvaliador(1);
		anuncio.setIdDonatario(1);
		anuncio.setNextIdAnuncio(1);
		anuncio.setNotaAvaliacao(2);
		anuncio.setQtdeItens(1);
		anuncio.setStatus(stat);
		anuncio.setTitulo("teste");
		anuncio.setUsuarioAnunciante(user);
		
		anuncio2.setCategoria(cat);
		anuncio2.setCategoriaUsuarioAnunciante(user.getCategoria());
		anuncio2.setDataCriacao(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		anuncio2.setDataExpiracao(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		anuncio2.setDataFim(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		anuncio2.setDescricao("teste");
		anuncio2.setFotos(fotos);
		anuncio2.setIdAnuncio(1);
		anuncio2.setIdAvaliador(1);
		anuncio2.setIdDonatario(1);
		anuncio2.setNextIdAnuncio(1);
		anuncio2.setNotaAvaliacao(2);
		anuncio2.setQtdeItens(1);
		anuncio2.setStatus(stat);
		anuncio2.setTitulo("teste");
		anuncio2.setUsuarioAnunciante(user);
		
		assertEquals(anuncio.hashCode(), anuncio2.hashCode());
		
	}
	
	@Test
	void TestToString() {
		Anuncio anuncio = new Anuncio(1, 1, "teste", "teste", 2.0);
		
		assertNotNull(anuncio.toString());

	}
	
	
}
