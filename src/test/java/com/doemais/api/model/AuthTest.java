package com.doemais.api.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.doemais.api.models.Auth;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthTest {

	@Test
	public void TestEquals() {
		Auth auth = new Auth();
		Auth auth2 = new Auth();
		
		auth.setEmail("teste@gmail.com");
		auth2.setEmail("teste@gmail.com");
		
		boolean res = auth.equals(auth2);
		
		assertTrue(res);
	}
	
	
	@Test
	public void TestNotEquals() {
		Auth auth = new Auth();
		Auth auth2 = new Auth();
		
		auth.setEmail("teste@gmail.com");
		auth2.setEmail("teste2@gmail.com");
		
		boolean res = auth.equals(auth2);
		
		assertTrue(res);
	}
	
}
