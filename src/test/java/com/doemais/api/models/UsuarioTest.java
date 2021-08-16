package com.doemais.api.models;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




@SpringBootTest
 class UsuarioTest {

	private Usuario usuario = new Usuario();
	
	@Test
	 void TestUsernameUsuario() {
		
		usuario.setUserName("Teste");
		
		assertEquals("Teste", usuario.getUserName());
		
	}
	
	
	@Test
	 void TestEquals() {
		Usuario usuario2 = new Usuario();
		
		usuario.setCpf("11111");
		usuario.setRg("22222");
		
		usuario2.setCpf("11111");
		usuario2.setRg("22222");
		
		boolean res = usuario.equals(usuario2);
		
		assertTrue(res);
	}
	
	@Test
	 void TestNotEquals() {
		Usuario usuario2 = new Usuario();
		
		usuario.setCpf("11111");
		usuario.setRg("22222");
		
		usuario2.setCpf("11111");
		usuario2.setRg("2222222");
		
		boolean res = usuario.equals(usuario2);
		
		assertTrue(res);
	}
	
	@Test
	 void TestHashCode() {
		Usuario usuario2 = new Usuario();
		
		usuario.setCpf("11111");
		usuario.setRg("22222");
		
		usuario2.setCpf("11111");
		usuario2.setRg("22222");
		
		assertEquals(usuario.hashCode(), usuario2.hashCode());
		
		
	}
	
	
	@Test
	 void TestConstrutor() {
		
		Usuario usuario = new Usuario("teste", "testando", "11111", null, null, "544545", "M", "teste");
	
		assertEquals("teste", usuario.getNome());
        assertEquals("testando", usuario.getUserName());
        assertEquals("11111", usuario.getCpf());
	}
	
}
