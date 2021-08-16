package com.doemais.api.models;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@SpringBootTest
 class AnuncioFotosTest {

	private AnuncioFotos anuncioFotos = new AnuncioFotos();
	private AnuncioFotos anuncioFotos2 = new AnuncioFotos();
	@Test
	 void TestAnuncioFoto()  {
		anuncioFotos.setFoto("Foto1");
		
		assertEquals("Foto1", anuncioFotos.getFoto());
		
	}
	
	
	@Test
	 void TestEquals()  {
		
		
		anuncioFotos.setFoto("Foto1");
		anuncioFotos2.setFoto("Foto1");
		
		boolean res = anuncioFotos.equals(anuncioFotos2);
		
		assertFalse(res);
	}
	
	@Test
	 void TestNotEquals()  {
	AnuncioFotos anuncioFotos2 = new AnuncioFotos();
		
		anuncioFotos.setFoto("Foto1");
		anuncioFotos2.setFoto("Fotografia1");
		
		boolean res = anuncioFotos.equals(anuncioFotos2);
		
		assertFalse(res);
	}
	
	@Test
	 void TestHashCode()  {

		
		anuncioFotos.setFoto("Foto");
		anuncioFotos.setIdAnuncioFotos(1);
		anuncioFotos2.setFoto("Foto");
		anuncioFotos2.setIdAnuncioFotos(1);
		
		assertNotEquals(anuncioFotos.hashCode(), anuncioFotos2.hashCode());
		
		
	}
	

	

	
}
