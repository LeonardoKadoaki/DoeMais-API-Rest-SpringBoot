package com.doemais.api.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
 class PerfilTest {
  
  @Test
   void TestEquals() {
	  
	  Perfil perfil = new Perfil();
	  Perfil perfil2 = new Perfil();
	  
	  perfil.setNome("teste");
	  perfil.setId(1L);
	  perfil2.setNome("teste");
	  perfil2.setId(1L);
	  
	  boolean res = perfil.equals(perfil2);
	  
	  
	  assertTrue(res);
  }
	
  
  @Test
   void TestNotEquals() {
	  
	  Perfil perfil = new Perfil();
	  Perfil perfil2 = new Perfil();
	  
	  perfil.setNome("teste");
	  perfil2.setNome("teste2");
	  
	  boolean res = perfil.equals(perfil2);
	  
	  
	  assertTrue(res);
  }
  
  @Test
  void TestHashCode() {
	  Perfil perfil = new Perfil();
	  Perfil perfil2 = new Perfil();
	  
	  perfil.setNome("teste");
	  perfil2.setNome("teste");
	  
	  assertEquals(perfil.hashCode(), perfil2.hashCode());
  }
  
  @Test
  void TestGetterAndSetter() {
	  Perfil perfil = new Perfil();
	  
	  perfil.setNome("teste");
      
      assertEquals("teste", perfil.getNome());
      assertNotNull(perfil.getAuthority());
	  
  }
	
}
