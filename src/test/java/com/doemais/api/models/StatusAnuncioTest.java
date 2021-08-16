package com.doemais.api.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
 class StatusAnuncioTest {

	@Test
	   void TestEquals() {
		  
		  StatusAnuncio status = new StatusAnuncio();
		  StatusAnuncio status2 = new StatusAnuncio();
		  
		  status.setDescricaoStatus("teste");
		  status2.setDescricaoStatus("teste");
		  
		  boolean res = status.equals(status2);
		  
		  
		  assertTrue(res);
	  }
	
	@Test
	  void TestNotEquals() {
		  
		  StatusAnuncio status = new StatusAnuncio();
		  StatusAnuncio status2 = new StatusAnuncio();
		  
		  status.setDescricaoStatus("teste");
		  status2.setDescricaoStatus("teste2");
		  
		  boolean res = status.equals(status2);
		  
		  
		  assertTrue(res);
	  }
	
	@Test
	void TestHashCode() {
		 StatusAnuncio status = new StatusAnuncio();
		  StatusAnuncio status2 = new StatusAnuncio();
		  
		  status.setDescricaoStatus("teste");
		  status2.setDescricaoStatus("teste");
		  
		  assertEquals(status.hashCode(), status2.hashCode());
	}
	
}
