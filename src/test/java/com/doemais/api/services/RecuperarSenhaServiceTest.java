package com.doemais.api.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailException;
import org.springframework.test.context.ContextConfiguration;

import com.doemais.api.dto.RecuperarSenhaDto;
import com.doemais.api.dto.ResetarSenhaDto;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.exception.EntidadeSemValorException;
import com.doemais.api.models.Auth;
import com.doemais.api.models.ResetarSenhaToken;
import com.doemais.api.models.Usuario;
import com.doemais.api.repository.AuthRepository;
import com.doemais.api.repository.MedalhaRepository;
import com.doemais.api.repository.ResetarSenhaTokenRepository;


@SpringBootTest
@ContextConfiguration(classes = {ResetarSenhaTokenRepository.class, RecuperarSenhaService.class})
class RecuperarSenhaServiceTest {

	@MockBean
	private RecuperarSenhaService recuperarSenhaService;
	
	@MockBean
	private ResetarSenhaTokenRepository resetarSenhaTokenRepository;
	
	private RecuperarSenhaDto recuperarSenhaDto;
	
	private ResetarSenhaDto resetarSenhaDto;
	
	private ResetarSenhaToken ResetarSenhaToken;
	
	@MockBean
	private AuthRepository authRepository;

	private Auth auth;
	
	private Usuario usuario;
	
	private Map<String, String> maps;

	@BeforeEach
    void Configuracao() throws ParseException {
		usuario = new Usuario();
		auth = new Auth();
		auth.setEmail("santos.g@aluno.ifsp.edu.br");
		auth.setIdAuth(1L);
		auth.setSenha("123");
		auth.setUsuario(usuario);
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date datas = formato.parse("27/06/2021");
		ResetarSenhaToken = new ResetarSenhaToken();
		ResetarSenhaToken.setId(1L);
		ResetarSenhaToken.setToken(UUID.randomUUID().toString());
		ResetarSenhaToken.setExpiryDate(30);
		ResetarSenhaToken.setExpiryDate(datas);
		ResetarSenhaToken.setAuth(auth);

		recuperarSenhaDto = new RecuperarSenhaDto();
		recuperarSenhaDto.setEmail(auth.getEmail());

		resetarSenhaDto = new ResetarSenhaDto();
		resetarSenhaDto.setToken(ResetarSenhaToken.getToken());
		resetarSenhaDto.setPassword("12345");
		resetarSenhaDto.setConfirmPassword("12345");
	}
	
	
	@Test
	void TestRecuperaEmail() throws MailException, EntidadeNaoEncontradaException, EntidadeSemValorException, MessagingException {
		Mockito.when(authRepository.findByEmail(recuperarSenhaDto.getEmail())).thenReturn(auth);
		
		RecuperarSenhaDto pass = recuperarSenhaService.enviaEmailRecuperaSenha(recuperarSenhaDto);
		
		assertNotEquals(pass, auth);
	}
	
	@Test
	void TestGetEmail() throws MailException, EntidadeNaoEncontradaException, EntidadeSemValorException {
		Mockito.when(resetarSenhaTokenRepository.findByToken(ResetarSenhaToken.getToken())).thenReturn(ResetarSenhaToken);
		
		ResetarSenhaToken token = recuperarSenhaService.retornaPaginaRedefineSenha(ResetarSenhaToken.getToken());
		
		assertNotEquals(token, ResetarSenhaToken);
		
	}
	
	@Test
	void TestRedefineSenhaLogin() throws EntidadeNaoEncontradaException, EntidadeSemValorException {
		Mockito.when(resetarSenhaTokenRepository.save(ResetarSenhaToken)).thenReturn(ResetarSenhaToken);
		
		ResetarSenhaDto resetDto = recuperarSenhaService.RedefineSenha(resetarSenhaDto);
		
		assertNull(resetDto);
		
		
	}
	
	
}
