package com.doemais.api.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.doemais.api.dto.EnderecoDto;
import com.doemais.api.dto.EnderecoType;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.models.Endereco;
import com.doemais.api.models.Usuario;
import com.doemais.api.repository.EnderecoRepository;
import com.doemais.api.services.EnderecoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/endereco")
@Api(tags = "Endereco")
public class EnderecoController {

	Logger logger = LoggerFactory.getLogger(EnderecoController.class);
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	EnderecoService enderecoService;

	@PersistenceContext
	private EntityManager em;

	@ApiOperation(value = "Retorna o endereço do usuário")
	@GetMapping("/usuario/{idUsuario}")
	public EnderecoDto consultarEnderecoDoUsuario(@PathVariable(value = "idUsuario") long idUsuario) throws EntidadeNaoEncontradaException {

		return enderecoService.consultarEnderecoUsuario(idUsuario);
	}
	
	@ApiOperation(value="Cadastra o endereço do usuário")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(produces = "application/json")
	public void cadastrarEnderecoUsuario(@RequestBody @Valid Endereco endereco) throws Exception {
		enderecoService.cadastrarEndereco(endereco);
	}
	
	@ApiOperation(value = "Retorna o endereço do usuário pelo cep")
	@GetMapping("/buscarEndereco/{cep}")
	public EnderecoType consultarEnderecoPorCep(@PathVariable(value = "cep") String cep) throws Exception{
		return enderecoService.pesquisarEndereco(cep);
	}
		
	@ApiOperation(value = "Deleta o endereço do usuário")
	@DeleteMapping("/usuario/{idUsuario}")
	public void deletarUsuario(@PathVariable(value = "idUsuario") long idUsuario) {
		enderecoRepository.deleteByUsuarioIdUsuario(idUsuario);
	}

	@ApiOperation(value = "Atualiza o endereço do usuário")
	@PutMapping("/usuario/{idUsuario}")
	public Endereco atualizarUsuario(@RequestBody @Valid Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
}
