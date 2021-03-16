package com.doemais.api.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.doemais.api.models.StatusAnuncio;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatusAnuncioTest {

	@Test
	  public void TestEquals() {
		  
		  StatusAnuncio status = new StatusAnuncio();
		  StatusAnuncio status2 = new StatusAnuncio();
		  
		  status.setDescricaoStatus("teste");
		  status2.setDescricaoStatus("teste");
		  
		  boolean res = status.equals(status2);
		  
		  
		  assertTrue(res);
	  }
	
	@Test
	  public void TestNotEquals() {
		  
		  StatusAnuncio status = new StatusAnuncio();
		  StatusAnuncio status2 = new StatusAnuncio();
		  
		  status.setDescricaoStatus("teste");
		  status2.setDescricaoStatus("teste2");
		  
		  boolean res = status.equals(status2);
		  
		  
		  assertTrue(res);
	  }
	
}
