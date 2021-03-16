package com.doemais.api.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.doemais.api.models.Usuario;
import com.doemais.api.models.UsuarioAuditoria;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioAuditoriaTest {

	private UsuarioAuditoria usuarioAuditoria = new UsuarioAuditoria(0, null, null, null, null, null, null, null, null, null, null, null, null);
	
	@Test
	public void TestNameUsuarioAuditoria() {
		
		usuarioAuditoria.setNome("teste");
		
		assertEquals("teste", usuarioAuditoria.getNome());
	}
	
	
	@Test
	public void TestEquals() {
		UsuarioAuditoria usuarioAuditoria2 = new UsuarioAuditoria(0, null, null, null, null, null, null, null, null, null, null, null, null);
		
		usuarioAuditoria.setCpf("11111");
		usuarioAuditoria.setRg("22222");
		
		usuarioAuditoria2.setCpf("11111");
		usuarioAuditoria2.setRg("22222");
		
		boolean res = usuarioAuditoria.equals(usuarioAuditoria2);
		
		assertTrue(res);
	}
	
	@Test
	public void TestNotEquals() {
		UsuarioAuditoria usuarioAuditoria2 = new UsuarioAuditoria(0, null, null, null, null, null, null, null, null, null, null, null, null);
		
		usuarioAuditoria.setCpf("11111");
		usuarioAuditoria.setRg("22222");
		
		usuarioAuditoria2.setCpf("11111");
		usuarioAuditoria2.setRg("2222222");
		
		boolean res = usuarioAuditoria.equals(usuarioAuditoria2);
		
		assertTrue(res);
	}
	
	@Test
	public void TestHashCode() {
		UsuarioAuditoria usuarioAuditoria2 = new UsuarioAuditoria(0, null, null, null, null, null, null, null, null, null, null, null, null);
		
		usuarioAuditoria.setCpf("11111");
		usuarioAuditoria.setRg("22222");
		
		usuarioAuditoria2.setCpf("11111");
		usuarioAuditoria2.setRg("22222");
		
		assertEquals(usuarioAuditoria.hashCode(), usuarioAuditoria2.hashCode());
			
	}
	
	
	@Test
	public void TestConstrutor() {
	 UsuarioAuditoria usuarioAuditoria = new UsuarioAuditoria(1, "teste", "111", "222", null, null, null, null, null, null, null, null, null);
		
	    assertEquals("teste", usuarioAuditoria.getNome());
        assertEquals("111", usuarioAuditoria.getCpf());
        assertEquals("222", usuarioAuditoria.getRg());
	}
	
	
	
	
	
	
}
