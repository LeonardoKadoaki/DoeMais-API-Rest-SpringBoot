package com.doemais.api.models;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
 class ResumoReputacaoUsuarioTest {

	@Test
	 void TestEquals() {
		ResumoReputacaoUsuario resumo = new ResumoReputacaoUsuario();
		ResumoReputacaoUsuario resumo2 = new ResumoReputacaoUsuario();
		
        resumo.setDescricaoResumo("TESTE");
        resumo.setNotaAvaliacao(5);
        
        resumo2.setDescricaoResumo("TESTE");
        resumo2.setNotaAvaliacao(5);
        
		boolean res = resumo.equals(resumo2);
		
		assertTrue(res);
		
	}
	
	@Test
	 void TestNotEquals() {
		ResumoReputacaoUsuario resumo = new ResumoReputacaoUsuario();
		ResumoReputacaoUsuario resumo2 = new ResumoReputacaoUsuario();
		
        resumo.setDescricaoResumo("TESTE");
        resumo.setNotaAvaliacao(5);
        
        resumo2.setDescricaoResumo("Admin");
        resumo2.setNotaAvaliacao(4);
        
		boolean res = resumo.equals(resumo2);
		
		assertTrue(res);
	}
	
	@Test
	 void TestGettersAndSetters() throws ParseException {
		ResumoReputacaoUsuario resumo = new ResumoReputacaoUsuario();
		Usuario usuario = new Usuario();
		
		DateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
		Date data2 = formato2.parse("05/02/1998");
        resumo.setNotaAvaliacao(5);
        resumo.setDataAtualizacaoRegistro(data2);
        resumo.setDescricaoResumo("teste");
        resumo.setIdResumo(1);
        resumo.setUsuario(usuario);
	
        
        
	    assertEquals(5, resumo.getNotaAvaliacao());
	    assertEquals("teste", resumo.getDescricaoResumo());
	    assertEquals(1, resumo.getIdResumo());
	    assertEquals(usuario, resumo.getUsuario());
	    assertEquals(data2, resumo.getDataAtualizacaoRegistro());

 


		
	}
	
	@Test
	void TestHashCode() {
		ResumoReputacaoUsuario resumo = new ResumoReputacaoUsuario();
		ResumoReputacaoUsuario resumo2 = new ResumoReputacaoUsuario();

		resumo.setDescricaoResumo("teste");
		resumo.setNotaAvaliacao(2);
		
		resumo2.setDescricaoResumo("teste");
		resumo2.setNotaAvaliacao(2);
		
		assertEquals(resumo.hashCode(), resumo2.hashCode());

	}
	
	@Test
	void TestToString() throws ParseException {
		DateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
		Date data2 = formato2.parse("05/02/1998");
		Usuario usuario = new Usuario();
		ResumoReputacaoUsuario resumo1 = new ResumoReputacaoUsuario(1, "teste", 2, data2, usuario);
		
		assertNotNull(resumo1.toString());
	}
	
	
}
