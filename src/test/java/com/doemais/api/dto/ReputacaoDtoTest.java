package com.doemais.api.dto;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ReputacaoDtoTest {

	@Test
	public void TestSettersAndGetters() {
	ReputacaoDto reputacao = new ReputacaoDto();
	
	    reputacao.setNotaAvaliacao(2);
	    reputacao.setPapelUsuario("Teste");
		
		assertEquals(2, reputacao.getNotaAvaliacao());
		assertEquals("Teste", reputacao.getPapelUsuario());	
				
	}
	
	@Test
	public void TestandoEquals() {
		ReputacaoDto reputacao = new ReputacaoDto();
		ReputacaoDto reputacao2 = new ReputacaoDto();
		
		reputacao.setNotaAvaliacao(2);
		reputacao.setPapelUsuario("Teste");
		
		reputacao2.setNotaAvaliacao(2);
		reputacao2.setPapelUsuario("Teste");
		
		boolean res = reputacao.equals(reputacao2);
		
		assertTrue(res);
	
	}
	
	@Test
	public void TestandoNotEquals() {
		ReputacaoDto reputacao = new ReputacaoDto();
		ReputacaoDto reputacao2 = new ReputacaoDto();
		
		reputacao.setNotaAvaliacao(2);
		reputacao.setPapelUsuario("Teste");
		
		reputacao2.setNotaAvaliacao(1);
		reputacao2.setPapelUsuario("Test");
		
		boolean res = reputacao.equals(reputacao2);
		
		assertFalse(res);
		
	}
	
	@Test
	public void TestHashCode() {
		ReputacaoDto reputacao = new ReputacaoDto();
		ReputacaoDto reputacao2 = new ReputacaoDto();
		
		reputacao.setNotaAvaliacao(2);
		reputacao.setPapelUsuario("Teste");
		
		reputacao2.setNotaAvaliacao(2);
		reputacao2.setPapelUsuario("Teste");

		
		assertEquals(reputacao.hashCode(), reputacao2.hashCode());
		
	}
	
	
	
	
	
	
}
