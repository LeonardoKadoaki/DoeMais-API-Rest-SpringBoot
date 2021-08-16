package com.doemais.api.dto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
class AnuncioFotosTypeTest {

	@Test
	 void TestSettersAndGetters() {
	AnuncioFotosType anuncio = new AnuncioFotosType();
	
		anuncio.setIdAnuncio(1);
		
		assertEquals(1, anuncio.getIdAnuncio());
				
	}
	
	@Test
	 void TestandoEquals() {
		AnuncioFotosType anuncio = new AnuncioFotosType();
		AnuncioFotosType anuncio2 = new AnuncioFotosType();
		
		anuncio.setIdAnuncio(1);
		
		anuncio2.setIdAnuncio(1);
		
		boolean res =anuncio.equals(anuncio2);
		
		assertFalse(res);
	
	}
	
	@Test
	 void TestandoNotEquals() {
		AnuncioFotosType anuncio = new AnuncioFotosType();
		AnuncioFotosType anuncio2 = new AnuncioFotosType();
		
		anuncio.setIdAnuncio(1);
		
		anuncio2.setIdAnuncio(2);
		
		boolean res = anuncio.equals(anuncio2);
		
		assertFalse(res);
		
	}
	
	@Test
	 void TestHashCode() {
		AnuncioFotosType anuncio = new AnuncioFotosType();
		AnuncioFotosType anuncio2 = new AnuncioFotosType();
		
		anuncio.setIdAnuncio(1);
		
		anuncio2.setIdAnuncio(1);
		
		assertNotEquals(anuncio.hashCode(), anuncio2.hashCode());
		
	}
	
	@Test
	 void TestSetterAndGetterNull() {
		AnuncioFotosType anuncio = new AnuncioFotosType();
		
		anuncio.setFotos(null);
			
		assertNull(null, anuncio.getFotos());
		
		
	}
	
	
	
	
}
