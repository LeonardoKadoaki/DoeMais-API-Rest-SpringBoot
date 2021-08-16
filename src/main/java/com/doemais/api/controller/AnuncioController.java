package com.doemais.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import com.doemais.api.dto.*;
import com.doemais.api.exception.*;
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

import com.doemais.api.exception.AnuncioException;
import com.doemais.api.models.Anuncio;
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
		return anuncioService.buscarAnunciosPaginacao(pagina, limite);
	}

	@ApiOperation(value = "Consulta o histórico de anúncios por idUsuario")
	@GetMapping("/usuario/{idUsuario}")
	public List<Anuncio> listarAnuncios(@PathVariable(value = "idUsuario") long idUsuario)
			throws EntidadeNaoEncontradaException {
		return anuncioService.buscarAnunciosPorUsuario(idUsuario);
	}

	@ApiOperation(value = "Consulta as informações de um anúncio")
	@GetMapping("/{idAnuncio}")
	public Anuncio listarAnuncioUnico(@PathVariable(value = "idAnuncio") long idAnuncio)
			throws EntidadeNaoEncontradaException {
		return anuncioService.buscarAnuncioPorId(idAnuncio);
	}

	@ApiOperation(value = "Cadastra um anúncio")
	@PostMapping
	public MessageObjectType cadastrarAnuncio(@RequestBody @Valid Anuncio anuncio)
			throws EntidadeNaoEncontradaException, ConflictException, MissaoException, MoedasException, AnuncioException {
		return anuncioService.criarAnuncio(anuncio, true);
	}

	@ApiOperation(value = "Deleta um anúncio")
	@DeleteMapping("/{idAnuncio}")
	public void deletarAnuncio(@PathVariable(value = "idAnuncio") long idAnuncio)
			throws EntidadeNaoEncontradaException {
		anuncioService.deletarAnuncio(idAnuncio);
	}

	@ApiOperation(value = "Atualiza um anúncio")
	@PutMapping("/{idAnuncio}")//NAO CONCORDO COM ESSA MERDA, VOU TIRAR NO PRODUTO FINAL
	public Anuncio atualizarAnuncio(@RequestBody @Valid Anuncio anuncio)
			throws EntidadeNaoEncontradaException, MissaoException, MoedasException {
		return anuncioService.salvarAnuncio(anuncio);
	}

	//TODO colocar isso no statusService
	@ApiOperation(value = "Consulta os status cadastrados")
	@GetMapping("/status/lista")
	public List<StatusAnuncio> listarStatus() {
		return statusAnuncioRepository.findAll();
	}

	@ApiOperation(value = "Consulta os anúncios por palavras-chave, com paginação. As palavras-chave são aplicadas no título e na descrição do anúncio")
	@GetMapping("/consultar-anuncios-por-palavras-chave-paginacao")
	public List<Anuncio> listarAnunciosPorPalavrasChave(
			@ApiParam(value="texto", required=true) @RequestParam("texto") String texto,
			@ApiParam(value="pagina", required=true) @RequestParam("pagina") final int pagina,
			@ApiParam(value="limite", required=true) @RequestParam("limite") final int limite)
			throws EntidadeNaoEncontradaException {
		return anuncioService.listarAnunciosPorPalavrasChave(texto, pagina, limite);
	}

	@ApiOperation(value = "Consulta os anúncios por cidade do anúncio com paginação")
	@GetMapping("/consultar-anuncios-por-cidade-paginacao")
	public List<Anuncio> listarAnunciosPorCidade(
			@ApiParam(value="cidade", required=true) @RequestParam("cidade") String cidade,
			@ApiParam(value="pagina", required=true) @RequestParam("pagina") final int pagina, 
			@ApiParam(value="limite", required=true) @RequestParam("limite") final int limite)
			throws EntidadeNaoEncontradaException {
		return anuncioService.listaAnunciosPorCidadeAnuncio(cidade, pagina, limite);
	}

	@ApiOperation(value = "Retorna a avaliação do anúncio")
	@GetMapping("/{idAnuncio}/avaliacao")
	public AvaliacaoType consultarAvaliacaoAnuncio(@PathVariable(value = "idAnuncio") long idAnuncio)
			throws EntidadeNaoEncontradaException {
		double a = anuncioService.getAvaliacaoAnuncio(idAnuncio);
		AvaliacaoType av = new AvaliacaoType();
		av.setAvaliacao(a);
		return av;
	}
	
	@ApiOperation(value = "Avalia o anúncio")
	@PostMapping("/avaliar")
	public Anuncio avaliarAnuncio(@RequestBody @Valid AvaliacaoDto av)
			throws EntidadeNaoEncontradaException, ConflictException {
		return anuncioService.registraAvaliacaoAnuncio(av);
	}
	
	@ApiOperation(value = "Altera o status do anúncio")
	@PostMapping("/alterar-status")
	public List<MessageMoedasObjectType> alterarStatusAnuncio(@RequestBody @Valid StatusAnuncioDto statusAnuncioDto)
			throws EntidadeNaoEncontradaException, ConflictException, MoedasException, MissaoException, MedalhaException {
		return anuncioService.alterarStatusAnuncio(statusAnuncioDto);
	}
	
	@ApiOperation(value = "Adiciona fotos do anúncio")
	@PostMapping("/fotos")
	public Anuncio adicionarFotosAnuncio(@RequestBody @Valid AnuncioFotosType anuncioFotos)
			throws EntidadeNaoEncontradaException {
		return anuncioService.cadastrarFotosAnuncio(anuncioFotos);
	}

	@ApiOperation(value = "Obtém o doador dos itens doados")
	@GetMapping("/{idAnuncio}/doador")
	public DoadorType doador(@PathVariable(value = "idAnuncio") long idAnuncio)
			throws EntidadeNaoEncontradaException {
		return anuncioService.doador(idAnuncio);
	}

	@ApiOperation(value = "Obtém o donatário dos itens recebidos")
	@GetMapping("/{idAnuncio}/donatario")
	public DonatarioType donatario(@PathVariable(value = "idAnuncio") long idAnuncio)
			throws EntidadeNaoEncontradaException {
		return anuncioService.donatario(idAnuncio);
	}

	@ApiOperation(value = "Registra interesse do usuário no anúncio")
	@PostMapping("/interesse")
	public void registrarInteresseAnuncio(@RequestBody @Valid InteresseType interesse)
			throws EntidadeNaoEncontradaException, ConflictException {
		anuncioService.registrarInteresseAnuncio(interesse);
	}
	
	@ApiOperation(value = "Consulta os interessados no anúncio")
	@GetMapping("/interessados/{idAnuncio}")
	public List<InteressadosAnuncioType> consultarInteressadosAnuncio(@PathVariable(value = "idAnuncio") long idAnuncio)
			throws EntidadeNaoEncontradaException {
		return anuncioService.consultarInteressadosAnuncio(idAnuncio);
	}

	@ApiOperation(value = "Retorna o indicador de itens restantes e o id do próximo item, se houver")
	@GetMapping("/itens-restantes/{idAnuncio}")
	public ItensRestantesType getItensRestantes(@PathVariable("idAnuncio") long idAnuncio)
			throws EntidadeNaoEncontradaException {
		return anuncioService.getItensRestantes(idAnuncio);
	}
}
