package com.doemais.api.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doemais.api.dto.EnderecoDto;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.models.Endereco;
import com.doemais.api.models.Usuario;
import com.doemais.api.repository.EnderecoRepository;
import com.doemais.api.services.EnderecoService;
import com.google.gson.Gson;

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
		return enderecoService.formatarEndereco(idUsuario);
	}
	
	@ApiOperation(value="Cadastra o endereço do usuário")
	@PostMapping(produces = "application/json")
	public Endereco cadastrarEnderecoParaUsuario(@RequestBody @Valid Endereco endereco) throws Exception {
		
		/*Consumindo API publica externa */

		URL url = new URL("https://viacep.com.br/ws/"+ endereco.getCep()+"/json/");
		URLConnection connection = url.openConnection();
		
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		String cep = "";
		StringBuilder jsonCep = new StringBuilder();
		while((cep = br.readLine()) != null) {
			jsonCep.append(cep);
		}
		
		
		Endereco enderecoAux = new Gson().fromJson(jsonCep.toString(), Endereco.class);
		
		endereco.setCep(enderecoAux.getCep());
		endereco.setLogradouro(enderecoAux.getLogradouro());
		endereco.setComplemento(enderecoAux.getComplemento());
		endereco.setBairro(enderecoAux.getBairro());
		endereco.setLocalidade(enderecoAux.getLocalidade());
		endereco.setUf(enderecoAux.getUf());
		
		
		return endereco;
	}
	
	@ApiOperation(value = "Deleta o endereço do usuário")
	@DeleteMapping
	public void deletarUsuario(@RequestBody Usuario usuario) throws EntidadeNaoEncontradaException {
		enderecoService.deletarEndereco(usuario);
	}

	@ApiOperation(value = "Atualiza o endereço do usuário")
	@PutMapping
	public Endereco atualizarUsuario(@RequestBody @Valid Endereco endereco) throws EntidadeNaoEncontradaException {
		return enderecoService.cadastrarEndereco(endereco);
	}
}
