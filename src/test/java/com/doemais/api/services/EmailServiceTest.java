package com.doemais.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailException;

import com.doemais.api.dto.EmailAddressType;
import com.doemais.api.dto.IdUsuarioType;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.models.Email;
import com.doemais.api.models.Usuario;
import com.doemais.api.repository.EmailRepository;


@SpringBootTest
class EmailServiceTest {
	
	@MockBean
	private EmailRepository emailRepository;
	
	@MockBean
	private EmailService emailService;
	
	
	
	private Email email;
	private List<Email> emails = new ArrayList<>();
	private Usuario usuario;
	private Usuario usuario2;
	private EmailAddressType emailType;
	
	@BeforeEach
	void Configuracao() {
		usuario = new Usuario();
		usuario2 = new Usuario();
		usuario.setIdUsuario(1);
		usuario2.setIdUsuario(2);
		
		
		email = new Email();
		email.setAssunto("teste");
		email.setCorpoEmail("teste");
		email.setIdEmail(1);
		email.setDataEnvioEmail(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		email.setUsuarioDest(usuario);
		email.setUsuarioRemet(usuario2);
		
		emailType = new EmailAddressType();
		emailType.setEmail("santos.g@aluno.ifsp.edu.br");
		
	}
	
	@Test
	void TestEnviaEmail() throws MailException, EntidadeNaoEncontradaException{
		Mockito.when(emailRepository.findByIdEmail(email.getIdEmail())).thenReturn(Optional.ofNullable(email));
		
		Email em = emailService.enviarEmail(email);
		
		assertNull(em);
	}
	
	@Test
	void TestConsultaEmail() throws MailException, EntidadeNaoEncontradaException{
		Mockito.when(emailRepository.findByIdEmail(email.getIdEmail())).thenReturn(Optional.ofNullable(email));
		
		Email em = emailService.consultarEmail(email.getIdEmail());
		
		assertNotEquals(email, em);
	}
	
	@Test
	void TestListaEmails() throws MailException, EntidadeNaoEncontradaException{
		Mockito.when(emailRepository.findAllEmails()).thenReturn(Optional.ofNullable(emails));
		
		List<Email> ems = emailService.listarEmails();
		
		assertTrue(ems.isEmpty());
	}
	
	
	@Test
	void TestListaEmailsEnviados() throws EntidadeNaoEncontradaException {
		Mockito.when(emailRepository.findById(usuario.getIdUsuario())).thenReturn(Optional.ofNullable(email));
		Mockito.when(emailRepository.findAllByIdUsuarioRemet(usuario)).thenReturn(Optional.ofNullable(emails));	
		
		List<Email> emailsEnv = emailService.listarEmailsEnviados(usuario.getIdUsuario());
		
		assertTrue(emailsEnv.isEmpty());
	}
	

	@Test
	void TestListaEmailsRecebidos() throws EntidadeNaoEncontradaException {
		Mockito.when(emailRepository.findById(usuario.getIdUsuario())).thenReturn(Optional.ofNullable(email));
		Mockito.when(emailRepository.findAllByIdUsuarioDest(usuario2)).thenReturn(Optional.ofNullable(emails));	
		
		List<Email> emailsReceb = emailService.listarEmailsRecebidos(usuario2.getIdUsuario());
		
		assertTrue(emailsReceb.isEmpty());
	}
	
	@Test
	void TestEnderecoEmail() throws EntidadeNaoEncontradaException {
		Mockito.when(emailRepository.findById(usuario.getIdUsuario())).thenReturn(Optional.ofNullable(email));
		Mockito.when(emailRepository.findEmailAddressByIdUsuario(usuario.getIdUsuario())).thenReturn(Optional.ofNullable("Email vazio"));
		
		EmailAddressType type = emailService.consultarEnderecoEmail(usuario.getIdUsuario());
		
		assertNull(type);
	}
	
	
	@Test
	void TestConsultaIdUsuarioEmail() throws EntidadeNaoEncontradaException {
		Mockito.when(emailRepository.findIdUsuarioByEmailAddress(emailType.getEmail())).thenReturn(Optional.ofNullable(null));
		
		 IdUsuarioType idType = emailService.consultarIdUsuario(emailType);
		
		assertNull(idType);
	}
	
	
	
	
}
