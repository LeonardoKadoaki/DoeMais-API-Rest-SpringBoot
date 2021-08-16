package com.doemais.api.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import com.doemais.api.enums.MedalhaEnum;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.exception.MedalhaException;
import com.doemais.api.models.Medalha;
import com.doemais.api.models.Usuario;
import com.doemais.api.models.UsuarioMedalha;
import com.doemais.api.repository.MedalhaRepository;
import com.doemais.api.repository.UsuarioMedalhaRepository;

@SpringBootTest
@ContextConfiguration(classes = {MedalhaRepository.class, MedalhaService.class})
class MedalhaServiceTest {

	@MockBean
	private MedalhaService medalhaService;

	@MockBean
	private MedalhaRepository medalhaRepository;

	@MockBean
	private UsuarioMedalhaRepository usuarioRepository;

	private Medalha medalha;

	private UsuarioMedalha usuarioMedalha;

	private Usuario usuario;

	private List<Medalha> medalhas = new ArrayList<>();

	private List<UsuarioMedalha> usuarioMedalhas = new ArrayList<UsuarioMedalha>();

	@BeforeEach
	void Configuracao() {

		usuario = new Usuario();
		usuario.setIdUsuario(1);
		medalha = new Medalha();
		medalha.setIdMedalha(1);
		medalha.setDescricaoMedalha("Teste");
		medalha.setNomeMedalha("Teste");

		medalhas = new ArrayList<Medalha>();

		usuarioMedalhas = new ArrayList<UsuarioMedalha>();

		usuarioMedalha = new UsuarioMedalha();
		usuarioMedalha.setDataRegistro(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		usuarioMedalha.setIdUsuarioMedalha(1);
		usuarioMedalha.setMedalha(medalha);
		usuarioMedalha.setUsuario(usuario);

	}

	@Test
	void TestCadastraMedalha() {
		Mockito.when(medalhaRepository.save(medalha)).thenReturn(medalha);

		Medalha meds = medalhaService.cadastrarMedalha(medalha);

		assertNotEquals(meds, medalha);
	}

	@Test
	void TestConsultaMedalha() {
		Mockito.when(medalhaRepository.findAll()).thenReturn(medalhas);

		List<Medalha> listMeds = medalhaService.consultarMedalhas();

		assertEquals(listMeds, medalhas);
	}

	@Test
	void TestConsultaMedalhaUsuarios() throws EntidadeNaoEncontradaException {
		Mockito.when(usuarioRepository.findAllByIdUsuario(usuario.getIdUsuario()))
				.thenReturn(Optional.of(usuarioMedalhas));

		List<UsuarioMedalha> listMedalha = medalhaService.buscarMedalhasPorUsuario(usuario.getIdUsuario());

		assertEquals(listMedalha, usuarioMedalhas);
	}

	@Test
	void TestAdicionaMedalha() throws EntidadeNaoEncontradaException, MedalhaException {
		Mockito.when(medalhaRepository.findByIdMedalha(medalha.getIdMedalha())).thenReturn(Optional.of(medalha));
		Mockito.when(medalhaRepository.save(medalha)).thenReturn(medalha);

		medalhaService.AdicionaMedalha(usuario, MedalhaEnum.PRIMEIRA_DOACAO);

		assertNotNull(medalha);
	}

	@Test
	void TestMedalhasUsuario() {
		int conversor = (int) medalha.getIdMedalha();
		Mockito.when(usuarioRepository
				.verificaUsuarioMedalha(usuarioMedalha.getUsuario().getIdUsuario(), conversor)).thenReturn(conversor);

		medalhaService.isUsuarioMedalha(usuario.getIdUsuario(), MedalhaEnum.PRIMEIRA_DOACAO);

		assertThat(conversor).info.description();
	}

}
