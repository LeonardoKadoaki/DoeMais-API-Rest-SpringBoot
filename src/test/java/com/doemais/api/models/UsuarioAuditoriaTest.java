package com.doemais.api.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsuarioAuditoriaTest {

	private UsuarioAuditoria usuarioAuditoria = new UsuarioAuditoria(1, null, null, null, null, null, null, null, null,
			null, null, null, null);

	@Test
	void TestSetterAndGetterAuditoria() throws ParseException {
		Usuario usuario = new Usuario();
		DateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
		Date data2 = formato2.parse("05/02/1998");

		usuarioAuditoria.setIdUsuarioAuditoria(1);
		usuarioAuditoria.setNome("teste");
		usuarioAuditoria.setCpf("123456789");
		usuarioAuditoria.setDataAuditoria(data2);
		usuarioAuditoria.setDataCadastro(data2);
		usuarioAuditoria.setDataNascimento(data2);
		usuarioAuditoria.setFotoPerfil("foto");
		usuarioAuditoria.setGenero("M");
		usuarioAuditoria.setNumeroCelular("11999999");
		usuarioAuditoria.setRg("6556545");
		usuarioAuditoria.setSobre("teste");
		usuarioAuditoria.setUserName("teste");
		usuarioAuditoria.setUsuario(usuario);

		assertEquals("teste", usuarioAuditoria.getNome());
		assertEquals(1, usuarioAuditoria.getIdUsuarioAuditoria());
		assertEquals("123456789", usuarioAuditoria.getCpf());
		assertEquals(data2, usuarioAuditoria.getDataAuditoria());
		assertEquals(data2, usuarioAuditoria.getDataCadastro());
		assertEquals(data2, usuarioAuditoria.getDataNascimento());
		assertEquals("foto", usuarioAuditoria.getFotoPerfil());
		assertEquals("11999999", usuarioAuditoria.getNumeroCelular());
		assertEquals("M", usuarioAuditoria.getGenero());
		assertEquals("teste", usuarioAuditoria.getSobre());
		assertEquals("teste", usuarioAuditoria.getUserName());
		assertEquals("6556545", usuarioAuditoria.getRg());
		assertEquals(usuario, usuarioAuditoria.getUsuario());

	}

	@Test
	void TestToString() {
		UsuarioAuditoria usuarioAuditoria = new UsuarioAuditoria(0, null, null, null, null, null, null, null, null,
				null, null, null, null);
		
		assertNotNull(usuarioAuditoria.toString());
		

	}

	@Test
	void TestEquals() {
		UsuarioAuditoria usuarioAuditoria2 = new UsuarioAuditoria(0, null, null, null, null, null, null, null, null,
				null, null, null, null);

		usuarioAuditoria.setCpf("11111");
		usuarioAuditoria.setRg("22222");

		usuarioAuditoria2.setCpf("11111");
		usuarioAuditoria2.setRg("22222");

		boolean res = usuarioAuditoria.equals(usuarioAuditoria2);

		assertFalse(res);
	}

	@Test
	void TestNotEquals() {
		UsuarioAuditoria usuarioAuditoria2 = new UsuarioAuditoria(0, null, null, null, null, null, null, null, null,
				null, null, null, null);

		usuarioAuditoria.setCpf("11111");
		usuarioAuditoria.setRg("22222");

		usuarioAuditoria2.setCpf("11111");
		usuarioAuditoria2.setRg("2222222");

		boolean res = usuarioAuditoria.equals(usuarioAuditoria2);

		assertFalse(res);
	}

	@Test
	void TestHashCode() {
		UsuarioAuditoria usuarioAuditoria2 = new UsuarioAuditoria(0, null, null, null, null, null, null, null, null,
				null, null, null, null);

		usuarioAuditoria.setCpf("11111");
		usuarioAuditoria.setRg("22222");

		usuarioAuditoria2.setCpf("11111");
		usuarioAuditoria2.setRg("22222");

		assertNotEquals(usuarioAuditoria.hashCode(), usuarioAuditoria2.hashCode());

	}

	@Test
	void TestConstrutor() {
		UsuarioAuditoria usuarioAuditoria = new UsuarioAuditoria(1, "teste", "111", "222", null, null, null, null, null,
				null, null, null, null);

		assertEquals("teste", usuarioAuditoria.getNome());
		assertEquals("111", usuarioAuditoria.getCpf());
		assertEquals("222", usuarioAuditoria.getRg());
	}

}
