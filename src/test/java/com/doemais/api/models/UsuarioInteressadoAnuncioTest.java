package com.doemais.api.models;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.doemais.api.enums.InteresseEnum;

@SpringBootTest
class UsuarioInteressadoAnuncioTest {

	private UsuarioInteressadoAnuncio usuarioInteressado = new UsuarioInteressadoAnuncio();
	private UsuarioInteressadoAnuncio usuarioInteressado2 = new UsuarioInteressadoAnuncio();
	private Usuario user = new Usuario();
	private Anuncio anuncio = new Anuncio();

	@Test
	void TestSettersAndGetters() {

		usuarioInteressado.setIdInteresseAnuncio(1);
		usuarioInteressado.setUsuario(user);
		usuarioInteressado.setAnuncio(anuncio);
		usuarioInteressado.setDataRegistro(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		usuarioInteressado.setStatus(InteresseEnum.INTERESSADO);

		assertEquals(1, usuarioInteressado.getIdInteresseAnuncio());
		assertEquals(user, usuarioInteressado.getUsuario());
		assertEquals(anuncio, usuarioInteressado.getAnuncio());
		/*assertNotEquals(LocalDateTime.of(LocalDate.now(), LocalTime.now()),
				usuarioInteressado.getDataRegistro());*/
		assertEquals(InteresseEnum.INTERESSADO, usuarioInteressado.getStatus());
		

	}

	@Test
	void TestEquals() throws ParseException {
		UsuarioInteressadoAnuncio usuarioInteressado2 = new UsuarioInteressadoAnuncio();

		usuarioInteressado.setDataRegistro(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		usuarioInteressado2.setDataRegistro(LocalDateTime.of(LocalDate.now(), LocalTime.now()));

		boolean res = usuarioInteressado.equals(usuarioInteressado2);

		assertFalse(res);
	}

	@Test
	void TestNotEquals() {
		UsuarioInteressadoAnuncio usuarioInteressado2 = new UsuarioInteressadoAnuncio();

		usuarioInteressado.setIdInteresseAnuncio(1);
		usuarioInteressado2.setIdInteresseAnuncio(2);

		boolean res = usuarioInteressado.equals(usuarioInteressado2);

		assertFalse(res);
	}

	@Test
	void TestHashCode() throws ParseException {

		usuarioInteressado.setIdInteresseAnuncio(1);
		usuarioInteressado2.setIdInteresseAnuncio(1);

		assertNotEquals(usuarioInteressado.hashCode(), usuarioInteressado2.hashCode());

	}

}
