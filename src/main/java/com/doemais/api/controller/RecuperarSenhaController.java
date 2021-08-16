package com.doemais.api.controller;

import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.doemais.api.dto.RecuperarSenhaDto;
import com.doemais.api.dto.ResetarSenhaDto;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.exception.EntidadeSemValorException;
import com.doemais.api.models.Auth;
import com.doemais.api.models.ResetarSenhaToken;
import com.doemais.api.services.RecuperarSenhaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/recuperar-senha")
@Api(tags = "RecuperarSenha")
public class RecuperarSenhaController {

	Logger logger = LoggerFactory.getLogger(RecuperarSenhaController.class);

	@PersistenceContext
	private EntityManager em;

	@Autowired
	RecuperarSenhaService recuperarSenhaService;

	@ApiOperation(value = "Recupera o Email do usuário e envia um link para redefinir a senha")
	@PostMapping("/recupera-email")
	public RecuperarSenhaDto processForgotPassword(@RequestBody @Valid RecuperarSenhaDto passForgotDto)
			throws EntidadeNaoEncontradaException, MailException, EntidadeSemValorException, MessagingException {
		return recuperarSenhaService.enviaEmailRecuperaSenha(passForgotDto);

	}

	@ApiOperation("Redireciona o usuário para redefinir a senha")
	@GetMapping("/redefinir-senha")
	public ResetarSenhaToken displayResetPassword(@RequestParam("token") String token)
			throws EntidadeNaoEncontradaException {
		return recuperarSenhaService.retornaPaginaRedefineSenha(token);
	}

	@ApiOperation("Redefinir a Senha do usuário")
	@PostMapping("/redefinir-senha")
	public ResetarSenhaDto handlePasswordReset(@RequestBody @Valid ResetarSenhaDto passResetDto)
			throws EntidadeNaoEncontradaException, EntidadeSemValorException {
		return recuperarSenhaService.RedefineSenha(passResetDto);

	}

}
