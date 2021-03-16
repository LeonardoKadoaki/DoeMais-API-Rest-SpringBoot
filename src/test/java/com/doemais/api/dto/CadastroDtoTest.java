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
public class CadastroDtoTest {

	@Test
	public void TestSettersAndGetters() {
	CadastroDto cadastroDto = new CadastroDto();
	
		cadastroDto.setUserName("testando");
		cadastroDto.setNome("TesteUnitario");
		cadastroDto.setCpf("12345678901");
		
		assertEquals("testando", cadastroDto.getUserName());
		assertEquals("TesteUnitario", cadastroDto.getNome());
		assertEquals("12345678901", cadastroDto.getCpf());
				
	}
	
	@Test
	public void TestandoEquals() {
		CadastroDto cadastroDto = new CadastroDto();
		CadastroDto cadastroDto2 = new CadastroDto();
		
		
		cadastroDto.setNome("guilherme");
		cadastroDto.setEmail("guilherme@gmail.com");
		
		cadastroDto2.setNome("guilherme");
		cadastroDto2.setEmail("guilherme@gmail.com");
		
		boolean res = cadastroDto.equals(cadastroDto2);
		
		assertTrue(res);
	
	}
	
	@Test
	public void TestandoNotEquals() {
		CadastroDto cadastroDto = new CadastroDto();
		CadastroDto cadastroDto2 = new CadastroDto();
		
		
		cadastroDto.setNome("guilherme");
		cadastroDto.setEmail("guilherme@gmail.com");
		
		cadastroDto2.setNome("guilherme");
		cadastroDto2.setEmail("guilherme@gmail.com");
		
		boolean res = cadastroDto.equals(cadastroDto2);
		
		assertTrue(res);
		
	}
	
	@Test
	public void TestHashCode() {
		CadastroDto cadastroDto = new CadastroDto();
		CadastroDto cadastroDto2 = new CadastroDto();
		
		
		cadastroDto.setNome("guilherme");
		cadastroDto.setEmail("guilherme@gmail.com");
		
		cadastroDto2.setNome("guilherme");
		cadastroDto2.setEmail("guilherme@gmail.com");
		
		assertEquals(cadastroDto.hashCode(), cadastroDto2.hashCode());
		
	}
	
	@Test
	public void TestSetterAndGetterNull() {
		CadastroDto cadastroDto = new CadastroDto();
		
		cadastroDto.setUserName(null);
		cadastroDto.setNome(null);
		cadastroDto.setCpf(null);
		
		assertNull(null, cadastroDto.getUserName());
		assertNull(null, cadastroDto.getNome());
		assertNull(null, cadastroDto.getCpf());
		
	}
	
	
	
	
}
