package com.doemais.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import com.doemais.api.dto.EmailAddressType;
import com.doemais.api.dto.IdUsuarioType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.models.Email;
import com.doemais.api.services.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/email")
@Api(tags = "Email")
public class EmailController {

	Logger logger = LoggerFactory.getLogger(EmailController.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private EmailService emailService;


	@ApiOperation(value="Serviço para enviar email")
	@PostMapping
	public Email enviarEmail(@RequestBody @Valid Email email)
			throws MailException, EntidadeNaoEncontradaException {

		return emailService.enviarEmail(email);
	}

//	@ApiOperation(value="Serviço para enviar email com anexo")
//	@PostMapping("/email-anexo")
//	public Email enviarEmailComAnexo(@RequestBody @Valid Email email)
//			throws MailException, EntidadeNaoEncontradaException, MessagingException {
//
//		return emailService.enviarEmailComAnexo(email);
//	}


	@ApiOperation(value = "Consulta um email específico")
	@GetMapping("/{idEmail}")
	public Email consultarEmail(@PathVariable(value = "idEmail") long idEmail)
			throws EntidadeNaoEncontradaException {

		return emailService.consultarEmail(idEmail);
	}

	@ApiOperation(value = "Lista todos os emails")
	@GetMapping
	public List<Email> listarEmails()
			throws EntidadeNaoEncontradaException {

		return emailService.listarEmails();
	}
	
	@ApiOperation(value = "Consulta o histórico de emails enviados por um usuário")
	@GetMapping("/usuario/{idUsuario}/emails-enviados")
	public List<Email> listarEmailsEnviados(@PathVariable(value = "idUsuario") long idUsuario)
			throws EntidadeNaoEncontradaException {

		return emailService.listarEmailsEnviados(idUsuario);
	}

	@ApiOperation(value = "Consulta o histórico de emails recebidos por um usuário")
	@GetMapping("/usuario/{idUsuario}/emails-recebidos")
	public List<Email> listarEmailsRecebidos(@PathVariable(value = "idUsuario") long idUsuario)
			throws EntidadeNaoEncontradaException {

		return emailService.listarEmailsRecebidos(idUsuario);
	}

	@ApiOperation(value = "Obtém o endereço de email de um usuário")
	@GetMapping("/usuario/{idUsuario}/endereco-email")
	public EmailAddressType consultarEnderecoEmail(@PathVariable(value = "idUsuario") long idUsuario)
			throws EntidadeNaoEncontradaException {

		return emailService.consultarEnderecoEmail(idUsuario);
	}

	@ApiOperation(value = "Obtém o id do usuário cadastrado com o endereço de email informado")
	@GetMapping("/usuario")
	public IdUsuarioType consultarIdUsuario(@RequestBody @Valid EmailAddressType email)
			throws EntidadeNaoEncontradaException {

		return emailService.consultarIdUsuario(email);
	}
}
