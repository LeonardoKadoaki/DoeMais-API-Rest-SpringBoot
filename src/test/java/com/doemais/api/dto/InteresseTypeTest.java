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
 class InteresseTypeTest {

	@Test
	 void TestSettersAndGetters() {
	InteresseType interesse = new InteresseType();
	
		interesse.setIdUsuario(1);
		interesse.setIdAnuncio(1);
		
		assertEquals(1, interesse.getIdUsuario());
		assertEquals(1, interesse.getIdAnuncio());
		
				
	}
	
	@Test
	 void TestandoEquals() {
		InteresseType interesse = new InteresseType();
		InteresseType interesse2 = new InteresseType();
		
		interesse.setIdUsuario(1);
		interesse.setIdAnuncio(1);
		
		interesse2.setIdUsuario(1);
		interesse2.setIdAnuncio(1);
		
		boolean res = interesse.equals(interesse2);
		
		assertFalse(res);
	
	}
	
	@Test
	 void TestandoNotEquals() {
		InteresseType interesse = new InteresseType();
		InteresseType interesse2 = new InteresseType();
		
		interesse.setIdUsuario(1);
		interesse.setIdAnuncio(1);
		
		interesse2.setIdUsuario(2);
		interesse2.setIdAnuncio(2);
		
		boolean res = interesse.equals(interesse2);
		
		assertFalse(res);
		
	}
	
	@Test
	 void TestHashCode() {
		InteresseType interesse = new InteresseType();
		InteresseType interesse2 = new InteresseType();
		
		interesse.setIdUsuario(1);
		interesse.setIdAnuncio(1);
		
		interesse2.setIdUsuario(1);
		interesse2.setIdAnuncio(1);
		
		assertNotEquals(interesse.hashCode(), interesse2.hashCode());
		
	}
	
	
}
