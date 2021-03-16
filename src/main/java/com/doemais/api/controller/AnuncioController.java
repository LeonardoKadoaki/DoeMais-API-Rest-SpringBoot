package com.doemais.api.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doemais.api.dto.AvaliacaoDto;
import com.doemais.api.dto.StatusAnuncioDto;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.models.Anuncio;
import com.doemais.api.models.AnuncioFotos;
import com.doemais.api.models.AvaliacaoType;
import com.doemais.api.models.StatusAnuncio;
import com.doemais.api.repository.AnuncioFotosRepository;
import com.doemais.api.repository.AnuncioRepository;
import com.doemais.api.repository.StatusAnuncioRepository;
import com.doemais.api.services.AnuncioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/anuncio")
@Api(tags = "Anuncio")
public class AnuncioController {

	Logger logger = LoggerFactory.getLogger(AnuncioController.class);

	@Autowired
	AnuncioRepository anuncioRepository;

	@Autowired
	StatusAnuncioRepository statusAnuncioRepository;
	
	@Autowired
	AnuncioFotosRepository anuncioFotosRepository;

	@Autowired
	AnuncioService anuncioService;

	@PersistenceContext
	private EntityManager em;

	@GetMapping("/consultar-anuncio-paginacao")
	@ApiOperation("Consulta todos os anúncios com paginação")
	public List<Anuncio> consultarPorCodigoPessoaPaginacao(
			@ApiParam(value="pagina", required=true) @RequestParam("pagina") final int pagina, 
			@ApiParam(value="limite", required=true) @RequestParam("limite") final int limite)
			throws EntidadeNaoEncontradaException {

		List<Anuncio> anuncios = anuncioService.buscarAnunciosPaginacao(pagina, limite);

		return anuncios;
	}

	@ApiOperation(value = "Consulta o histórico de anúncios por idUsuario")
	@GetMapping("/usuario/{idUsuario}")
	public List<Anuncio> listarAnuncios(@PathVariable(value = "idUsuario") long idUsuario)
			throws EntidadeNaoEncontradaException {
		return anuncioService.buscarAnunciosPorUsuario(idUsuario);
	}

	@ApiOperation(value = "Consulta as informações de um anúncio")
	@GetMapping("/{idAnuncio}")
	public Anuncio listarAnuncioUnico(@PathVariable(value = "idAnuncio") long idAnuncio) throws EntidadeNaoEncontradaException {
		return anuncioService.buscarAnuncioPorId(idAnuncio);
	}

	@ApiOperation(value = "Cadastra um anúncio")
	@PostMapping
	public Anuncio cadastrarAnuncio(@RequestBody @Valid Anuncio anuncio) throws EntidadeNaoEncontradaException {
		return anuncioService.cadastrarAnuncio(anuncio);
	}

	@ApiOperation(value = "Deleta um anúncio")
	@DeleteMapping
	public void deletarAnuncio(@RequestBody @Valid Anuncio anuncio) throws EntidadeNaoEncontradaException {
		anuncioService.deletarAnuncio(anuncio);
	}

	@ApiOperation(value = "Atualiza um anúncio")
	@PutMapping
	public Anuncio atualizarAnuncio(@RequestBody @Valid Anuncio anuncio) throws EntidadeNaoEncontradaException {
		return anuncioService.salvarAnuncio(anuncio);
	}

	//TODO colocar isso no statusService
	@ApiOperation(value = "Consulta os status cadastrados")
	@GetMapping("/status/lista")
	public List<StatusAnuncio> listarStatus() {
		return statusAnuncioRepository.findAll();
	}

	@ApiOperation(value = "Consulta os anúncios por título do anúncio com paginação")
	@GetMapping("/consultar-anuncios-por-titulo-paginacao")
	public List<Anuncio> listarAnunciosPorTitulo(
			@ApiParam(value="titulo", required=true) @RequestParam("titulo") String titulo, 
			@ApiParam(value="pagina", required=true) @RequestParam("pagina") final int pagina, 
			@ApiParam(value="limite", required=true) @RequestParam("limite") final int limite) throws EntidadeNaoEncontradaException {
		return anuncioService.listaAnunciosPorTituloAnuncio(titulo, pagina, limite);
	}

	@ApiOperation(value = "Consulta os anúncios por cidade do anúncio com paginação")
	@GetMapping("/consultar-anuncios-por-cidade-paginacao")
	public List<Anuncio> listarAnunciosPorCidade(
			@ApiParam(value="cidade", required=true) @RequestParam("cidade") String cidade,
			@ApiParam(value="pagina", required=true) @RequestParam("pagina") final int pagina, 
			@ApiParam(value="limite", required=true) @RequestParam("limite") final int limite) throws EntidadeNaoEncontradaException {
		return anuncioService.listaAnunciosPorCidadeAnuncio(cidade, pagina, limite);
	}

	//TODO consertar. Não pode ser retornado um double que não esteja no formato JSON
	@ApiOperation(value = "Retorna a avaliação do anúncio")
	@GetMapping("/{idAnuncio}/avaliacao")
	public AvaliacaoType consultarAvaliacaoAnuncio(@PathVariable(value = "idAnuncio") long idAnuncio) throws EntidadeNaoEncontradaException {
		double a = anuncioService.getAvaliacaoAnuncio(idAnuncio);
		AvaliacaoType ava = new AvaliacaoType();
		ava.setAvaliacao(a);
		return ava;
	}

	@ApiOperation(value = "Avalia o anúncio")
	@PostMapping("/avaliar")
	public Anuncio avaliarAnuncio(@RequestBody @Valid AvaliacaoDto av) throws EntidadeNaoEncontradaException {
		return anuncioService.registraAvaliacaoAnuncio(av);
	}
	
	@ApiOperation(value = "Altera o status do anúncio")
	@PostMapping("/alterar-status")
	public Anuncio alterarStatusAnuncio(@RequestBody @Valid StatusAnuncioDto statusAnuncioDto) throws EntidadeNaoEncontradaException {
		return anuncioService.alterarStatusAnuncio(statusAnuncioDto);
	}
	
	@ApiOperation(value = "Adiciona fotos")
	@PostMapping("/foto")
	public AnuncioFotos adicionarFotosAnuncio(@RequestBody @Valid AnuncioFotos anuncioFotos) throws EntidadeNaoEncontradaException {
		return anuncioFotosRepository.save(anuncioFotos);
	}
	
}
