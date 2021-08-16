package com.doemais.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.models.Medalha;
import com.doemais.api.services.MedalhaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/medalha")
@Api(tags = "Medalha")
public class MedalhaController {

	@Autowired
	MedalhaService medalhaService;
	
	@ApiOperation(value = "Consultar medalhas obtidas")
	@GetMapping
	public List<Medalha> consultarMedalhas(){
		return medalhaService.consultarMedalhas();
	}
	
	@ApiOperation(value = "Cadastra uma medalha")
	@PostMapping
	public Medalha cadastrarMedalha(@RequestBody @Valid Medalha medalha){
		return medalhaService.cadastrarMedalha(medalha);
	}
	
	
}
