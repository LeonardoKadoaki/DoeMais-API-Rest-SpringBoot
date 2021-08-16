package com.doemais.api.dto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




@SpringBootTest
 class EnderecoTypeTest {

	@Test
	 void TestSettersAndGetters() {
	EnderecoType endereco = new EnderecoType();
	
		endereco.setCep("1111111");
		endereco.setBairro("Teste");
		endereco.setLocalidade("Teste");
		
		assertEquals("1111111", endereco.getCep());
		assertEquals("Teste", endereco.getBairro());
		assertEquals("Teste", endereco.getLocalidade());
				
	}
	
	@Test
	 void TestandoEquals() {
		EnderecoType endereco = new EnderecoType();
		EnderecoType endereco2 = new EnderecoType();
		
		endereco.setBairro("teste");
		endereco.setCep("0000000");
		
		endereco2.setBairro("teste");
		endereco2.setCep("0000000");
		
		boolean res = endereco.equals(endereco2);
		
		assertFalse(res);
	
	}
	
	@Test
	 void TestandoNotEquals() {
		EnderecoType endereco = new EnderecoType();
		EnderecoType endereco2 = new EnderecoType();
		
		endereco.setBairro("teste");
		endereco.setCep("0000000");
		
		endereco2.setBairro("test");
		endereco2.setCep("000000");
		
		boolean res = endereco.equals(endereco2);
		
		assertFalse(res);
		
		
	}
	
	@Test
	 void TestHashCode() {
		EnderecoType endereco = new EnderecoType();
		EnderecoType endereco2 = new EnderecoType();
		
		endereco.setBairro("teste");
		endereco.setCep("0000000");
		
		endereco2.setBairro("teste");
		endereco2.setCep("0000000");
		
		assertNotEquals(endereco.hashCode(), endereco2.hashCode());
		
	}
	


	
}
