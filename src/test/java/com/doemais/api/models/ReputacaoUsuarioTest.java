package com.doemais.api.models;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




@SpringBootTest
 class ReputacaoUsuarioTest {

	private ReputacaoUsuario reputacao = new ReputacaoUsuario();
    private Usuario usuario = new Usuario();
    
	@Test
	 void TestNotaAvaliacaoReputacaoUsuario() {

		
		reputacao.setNotaAvaliacao(2);
		reputacao.setDataRegistro(LocalDate.of(2021, 07, 02));
		reputacao.setIdAvaliacao(1);
		reputacao.setIdAvaliador(1);
		reputacao.setPapelUsuario("teste");
		reputacao.setUsuario(usuario);

		assertEquals(2, reputacao.getNotaAvaliacao());
		assertEquals(LocalDate.of(2021, 07, 02), reputacao.getDataRegistro());
		assertEquals(1, reputacao.getIdAvaliacao());
		assertEquals(1, reputacao.getIdAvaliador());
		assertEquals("teste", reputacao.getPapelUsuario());
		assertEquals(usuario, reputacao.getUsuario());


	}

	@Test
	 void TestEquals() {
		ReputacaoUsuario reputacao2 = new ReputacaoUsuario();

		reputacao.setNotaAvaliacao(2);
		reputacao.setPapelUsuario("ADMIN");

		reputacao2.setNotaAvaliacao(2);
		reputacao2.setPapelUsuario("ADMIN");

		boolean res = reputacao.equals(reputacao2);

		assertTrue(res);
	}

	@Test
	 void TestNotEquals() {
		ReputacaoUsuario reputacao2 = new ReputacaoUsuario();

		reputacao.setNotaAvaliacao(2);
		reputacao.setPapelUsuario("ADMIN");

		reputacao2.setNotaAvaliacao(1);
		reputacao2.setPapelUsuario("USER");

		boolean res = reputacao.equals(reputacao2);

		assertTrue(res);
	}

	@Test
	 void TestHashCode() {

		ReputacaoUsuario reputacao2 = new ReputacaoUsuario();

		reputacao.setNotaAvaliacao(2);
		reputacao.setPapelUsuario("ADMIN");

		reputacao2.setNotaAvaliacao(1);
		reputacao2.setPapelUsuario("USER");

		assertEquals(reputacao.hashCode(), reputacao2.hashCode());

	}
	
	@Test
	void TestToString() {
		reputacao.setNotaAvaliacao(2);
		reputacao.setDataRegistro(LocalDate.of(2021, 07, 02));
		reputacao.setIdAvaliacao(1);
		reputacao.setIdAvaliador(1);
		reputacao.setPapelUsuario("teste");
		reputacao.setUsuario(usuario);
		
		assertNotNull(reputacao.toString());
	}

}
