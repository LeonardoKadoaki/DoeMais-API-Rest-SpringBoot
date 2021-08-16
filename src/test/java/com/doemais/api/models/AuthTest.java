package com.doemais.api.models;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
 class AuthTest {

	@Test
	 void TestEquals() {
		Auth auth = new Auth();
		Auth auth2 = new Auth();
		
		auth.setEmail("teste@gmail.com");
		auth2.setEmail("teste@gmail.com");
		
		boolean res = auth.equals(auth2);
		
		assertTrue(res);
	}
	
	
	@Test
	void TestNotEquals() {
		Auth auth = new Auth();
		Auth auth2 = new Auth();
		
		auth.setEmail("teste@gmail.com");
		auth2.setEmail("teste2@gmail.com");
		
		boolean res = auth.equals(auth2);
		
		assertTrue(res);
	}
	
	@Test
	void TestConstrutor() {
		Usuario user = new Usuario();
	    Auth auth = new Auth(user, "teste", "teste");
	    
	    assertEquals(user, auth.getUsuario());
	    assertEquals("teste", auth.getEmail());
	    assertEquals("teste", auth.getSenha());
	}
	
	@Test
	void TestHashCode() {
		Usuario user = new Usuario();
	    Auth auth = new Auth(user, "teste", "teste");
	    Auth auth2 = new Auth(user, "teste", "teste");

	    assertEquals(auth.hashCode(), auth2.hashCode());
	    
	    

	}
	
}
