package com.doemais.api.dto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class EnderecoDtoTest {

	@Test
	public void TestSettersAndGetters() {
	EnderecoDto endereco = new EnderecoDto();
	
		endereco.setCep("1111111");
		endereco.setBairro("Teste");
		endereco.setLocalidade("Teste");
		
		assertEquals("1111111", endereco.getCep());
		assertEquals("Teste", endereco.getBairro());
		assertEquals("Teste", endereco.getLocalidade());
				
	}
	
	@Test
	public void TestandoEquals() {
		EnderecoDto endereco = new EnderecoDto();
		EnderecoDto endereco2 = new EnderecoDto();
		
		endereco.setBairro("teste");
		endereco.setCep("0000000");
		
		endereco2.setBairro("teste");
		endereco2.setCep("0000000");
		
		boolean res = endereco.equals(endereco2);
		
		assertTrue(res);
	
	}
	
	@Test
	public void TestandoNotEquals() {
		EnderecoDto endereco = new EnderecoDto();
		EnderecoDto endereco2 = new EnderecoDto();
		
		endereco.setBairro("teste");
		endereco.setCep("0000000");
		
		endereco2.setBairro("test");
		endereco2.setCep("000000");
		
		boolean res = endereco.equals(endereco2);
		
		assertTrue(res);
		
		
	}
	
	@Test
	public void TestHashCode() {
		EnderecoDto endereco = new EnderecoDto();
		EnderecoDto endereco2 = new EnderecoDto();
		
		endereco.setBairro("teste");
		endereco.setCep("0000000");
		
		endereco2.setBairro("teste");
		endereco2.setCep("0000000");
		
		assertEquals(endereco.hashCode(), endereco2.hashCode());
		
	}
	


	
}
