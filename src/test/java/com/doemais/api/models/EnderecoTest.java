package com.doemais.api.models;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@SpringBootTest
class EnderecoTest {

	private  Endereco endereco = new Endereco();
	
	@Test
	 void TestCepEndereco()  {
		
		endereco.setCep("12345678901");
		
		assertEquals("12345678901", endereco.getCep());
		
	}
	
	
	@Test
	 void TestEquals()  {
		Endereco endereco2 = new Endereco();
		
	    endereco.setCep("12345678901");
	    endereco.setNumero(1500);
	    endereco.setUf("SP");
	    
	    endereco2.setCep("12345678901");
	    endereco2.setNumero(1500);
	    endereco2.setUf("SP");
				
		boolean res = endereco.equals(endereco2);
		
		assertTrue(res);
	}
	
	@Test
	 void TestNotEquals()  {
        Endereco endereco2 = new Endereco();
		
	    endereco.setCep("12345678901");
	    endereco.setNumero(1500);
	    endereco.setUf("SP");
	    
	    endereco2.setCep("24864134135");
	    endereco2.setNumero(2000);
	    endereco2.setUf("PR");
				
		boolean res = endereco.equals(endereco2);
		
		assertTrue(res);
	}
	
	@Test
	 void TestHashCode()  {
        Endereco endereco2 = new Endereco();
		
	    endereco.setCep("12345678901");
	    endereco.setNumero(1500);
	    endereco.setUf("SP");
	    
	    endereco2.setCep("12345678901");
	    endereco2.setNumero(1500);
	    endereco2.setUf("SP");
		
		assertEquals(endereco.hashCode(), endereco2.hashCode());
		
		
	}
	

	
}
