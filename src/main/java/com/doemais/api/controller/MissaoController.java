package com.doemais.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doemais.api.models.Missao;
import com.doemais.api.services.MissaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/missao")
@Api(tags = "Missao")
public class MissaoController {

	@Autowired
	MissaoService missaoService;
	
	@ApiOperation(value = "Cadastra uma missão")
	@PostMapping
	public Missao cadastrarMissao(@RequestBody @Valid Missao missao){
		return missaoService.cadastrarMissao(missao);
	}
	
	@ApiOperation(value = "Consulta missões cadastradas")
	@GetMapping
	public List<Missao> consultarMissoes(){
		return missaoService.consultarMissoes();
	}
}
