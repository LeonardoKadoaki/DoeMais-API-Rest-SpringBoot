package com.doemais.api.dto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




@SpringBootTest
 class InteressadosAnuncioTypeTest {

	@Test
	 void TestSettersAndGetters() {
	InteressadosAnuncioType interessados = new InteressadosAnuncioType();
	
		interessados.setNome("Guilherme");
		
		assertEquals("Guilherme", interessados.getNome());
				
	}
	
	@Test
	 void TestandoEquals() {
		InteressadosAnuncioType interessados = new InteressadosAnuncioType();
		InteressadosAnuncioType interessados2 = new InteressadosAnuncioType();
		
		interessados.setNome("Guilherme");
		interessados2.setNome("Guilherme");
		
		boolean res = interessados.equals(interessados2);
		
		assertFalse(res);
	
	}
	
	@Test
	 void TestandoNotEquals() {
		InteressadosAnuncioType interessados = new InteressadosAnuncioType();
		InteressadosAnuncioType interessados2 = new InteressadosAnuncioType();
		
		interessados.setNome("Guilherme");
		interessados2.setNome("Santos");
		
		boolean res = interessados.equals(interessados2);
		assertFalse(res);
		
	}
	
	@Test
	 void TestHashCode() {
		
		InteressadosAnuncioType interessados = new InteressadosAnuncioType();
		InteressadosAnuncioType interessados2 = new InteressadosAnuncioType();
		
		interessados.setNome("Guilherme");
		interessados2.setNome("Guilherme");
		
		assertNotEquals(interessados.hashCode(), interessados2.hashCode());
		
	}
	
	
}
