package com.doemais.api.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doemais.api.forms.LoginForm;
import com.doemais.api.security.dto.TokenDto;
import com.doemais.api.services.AuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
@Api(tags="Auth")
public class AuthController {
	
	Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private AuthService authService;
	
	
	@PostMapping
	@ApiOperation(value="Autentica o usuário")
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
		return authService.autenticaUsuario(form);
	}
	
}
