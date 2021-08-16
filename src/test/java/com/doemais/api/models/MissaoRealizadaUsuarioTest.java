package com.doemais.api.models;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class MissaoRealizadaUsuarioTest {

	private MissaoRealizadaUsuario missaoRealizada = new MissaoRealizadaUsuario();

	@Test
	void TestDataRegistroMissaoUsuario() throws ParseException {

		missaoRealizada.setDataRegistro(LocalDateTime.of(LocalDate.now(), LocalTime.now()));

		assertNotNull(missaoRealizada.getDataRegistro());

	}

	@Test
	void TestEquals() throws ParseException {
		MissaoRealizadaUsuario missaoRealizada2 = new MissaoRealizadaUsuario();

		missaoRealizada.setDataRegistro(LocalDateTime.of(LocalDate.now(), LocalTime.now()));

		missaoRealizada2.setDataRegistro(LocalDateTime.of(LocalDate.now(), LocalTime.now()));

		boolean res = missaoRealizada.equals(missaoRealizada2);

		assertTrue(res);
	}

	@Test
	void TestNotEquals() throws ParseException {
		MissaoRealizadaUsuario missaoRealizada2 = new MissaoRealizadaUsuario();

		missaoRealizada.setDataRegistro(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		missaoRealizada.setIdMissaoRealizada(1);

		missaoRealizada2.setDataRegistro(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        missaoRealizada2.setIdMissaoRealizada(1);
		boolean res = missaoRealizada.equals(missaoRealizada2);

		assertTrue(res);
	}

	@Test
	void TestHashCode() throws ParseException {
		MissaoRealizadaUsuario missaoRealizada2 = new MissaoRealizadaUsuario();

		missaoRealizada.setDataRegistro(LocalDateTime.of(LocalDate.now(), LocalTime.now()));

		missaoRealizada2.setDataRegistro(LocalDateTime.of(LocalDate.now(), LocalTime.now()));

		assertEquals(missaoRealizada.hashCode(), missaoRealizada2.hashCode());

	}

	@Test
	void TestConstrutor() {
		Usuario user = new Usuario();
		Missao miss = new Missao();
		MissaoRealizadaUsuario missao = new MissaoRealizadaUsuario(LocalDateTime.of(LocalDate.now(), LocalTime.now()),
				user, miss);
		
		assertEquals(user, missao.getUsuario());
		assertEquals(miss, missao.getMissao());

	}
	
	@Test
	void TestToString() {
		Usuario user = new Usuario();
		Missao miss = new Missao();
		MissaoRealizadaUsuario missao = new MissaoRealizadaUsuario(LocalDateTime.of(LocalDate.now(), LocalTime.now()),
				user, miss);
		
		assertNotNull(missao.toString());
	}

}
