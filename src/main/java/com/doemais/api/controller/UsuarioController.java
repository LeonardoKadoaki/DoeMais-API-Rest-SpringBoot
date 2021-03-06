package com.doemais.api.controller;

import java.util.List;

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

import com.doemais.api.dto.AvaliacaoType;
import com.doemais.api.dto.CadastroDto;
import com.doemais.api.dto.ReputacaoDto;
import com.doemais.api.exception.ConflictException;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.models.Anuncio;
import com.doemais.api.models.Medalha;
import com.doemais.api.models.ReputacaoUsuario;
import com.doemais.api.models.Usuario;
import com.doemais.api.models.UsuarioMedalha;
import com.doemais.api.repository.AuthRepository;
import com.doemais.api.repository.MedalhaRepository;
import com.doemais.api.repository.ReputacaoRepository;
import com.doemais.api.repository.ResumoReputacaoRepository;
import com.doemais.api.repository.UsuarioMedalhaRepository;
import com.doemais.api.repository.UsuarioRepository;
import com.doemais.api.services.MedalhaService;
import com.doemais.api.services.MissaoService;
import com.doemais.api.services.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/usuario")
@Api(tags = "Usuario")
public class UsuarioController {

	Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	AuthRepository authRepository;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	ReputacaoRepository reputacaoRepository;
	
	@Autowired
	ResumoReputacaoRepository resumoReputacaoRepository;
	
	@Autowired
	MedalhaService medalhaService;
	
	@PersistenceContext
	private EntityManager em;

	@ApiOperation(value = "Retorna uma lista de usu??rios")
	@GetMapping("/lista")
	public List<Usuario> listarUsuarios() {
		return usuarioService.listaTodosUsuarios();
	}

	@ApiOperation(value = "Retorna as informa????es de perfil do usu??rio")
	@GetMapping("/perfil/{idUsuario}")
	public Usuario listarUsuarioUnico(@PathVariable(value = "idUsuario") long idUsuario)
			throws EntidadeNaoEncontradaException {
		return usuarioService.buscarUsuarioPorId(idUsuario);
	}

	@ApiOperation(value="Cadastra um usu??rio")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Usuario cadastrarUsuario(@RequestBody @Valid CadastroDto cadastro)
			throws ConflictException {
		return usuarioService.cadastrarUsuario(cadastro);
	}
	
	@ApiOperation(value = "Deleta um usu??rio")
	@DeleteMapping("/perfil/{idUsuario}")
	public void deletarUsuario(@PathVariable(value = "idUsuario") long idUsuario)
			throws EntidadeNaoEncontradaException {
		usuarioService.deletarUsuario(idUsuario);
	}

	@ApiOperation(value = "Atualiza um usu??rio")
	@PutMapping("/perfil/{idUsuario}")
	public Usuario atualizarUsuario(@RequestBody @Valid Usuario usuario)
			throws ConflictException {
		return usuarioService.atualizarUsuario(usuario);
	}

	@ApiOperation(value = "Retorna avalia????o de um usu??rio")
	@GetMapping("/perfil/{idUsuario}/avaliacao")
	public AvaliacaoType consultarAvaliacaoUsuario(@PathVariable("idUsuario") long idUsuario)
			throws EntidadeNaoEncontradaException {
		return usuarioService.getAvaliacaoUsuario(idUsuario);
	}

	@ApiOperation(value="Avalia um usu??rio")
	@PostMapping("/perfil/{idUsuario}/avaliar")
	public ReputacaoUsuario avaliarUsuario(@RequestBody @Valid ReputacaoDto reputacao,
										   @PathVariable("idUsuario") long idUsuario)
			throws EntidadeNaoEncontradaException, ConflictException {
		return usuarioService.registraAvaliacaoUsuario(reputacao, idUsuario);
	}
	
	@ApiOperation(value="Retornar medalhas obtidas por usu??rio")
	@GetMapping("/perfil/{idUsuario}/medalha")
	public List<UsuarioMedalha> consultaMedalhaUsuario(@PathVariable("idUsuario") long idUsuario)
			throws EntidadeNaoEncontradaException {
		return medalhaService.buscarMedalhasPorUsuario(idUsuario);
	}
}
