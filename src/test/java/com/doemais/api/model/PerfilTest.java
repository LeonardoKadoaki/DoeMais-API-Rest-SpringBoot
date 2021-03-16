package com.doemais.api.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.doemais.api.models.Perfil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PerfilTest {
  
  @Test
  public void TestEquals() {
	  
	  Perfil perfil = new Perfil();
	  Perfil perfil2 = new Perfil();
	  
	  perfil.setNome("teste");
	  perfil2.setNome("teste");
	  
	  boolean res = perfil.equals(perfil2);
	  
	  
	  assertTrue(res);
  }
	
  
  @Test
  public void TestNotEquals() {
	  
	  Perfil perfil = new Perfil();
	  Perfil perfil2 = new Perfil();
	  
	  perfil.setNome("teste");
	  perfil2.setNome("teste2");
	  
	  boolean res = perfil.equals(perfil2);
	  
	  
	  assertTrue(res);
  }
	
}
