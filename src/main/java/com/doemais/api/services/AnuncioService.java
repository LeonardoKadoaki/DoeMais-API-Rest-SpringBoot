package com.doemais.api.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.doemais.api.dto.AnuncioFotosType;
import com.doemais.api.dto.AvaliacaoDto;
import com.doemais.api.dto.StatusAnuncioDto;
import com.doemais.api.enums.StatusAnuncioEnum;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.models.Anuncio;
import com.doemais.api.models.AnuncioFotos;
import com.doemais.api.models.StatusAnuncio;
import com.doemais.api.repository.AnuncioRepository;
import com.doemais.api.repository.CategoriaRepository;
import com.doemais.api.repository.StatusAnuncioRepository;
import com.doemais.api.repository.UsuarioRepository;

@Service
public class AnuncioService {

	Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@PersistenceContext
	private EntityManager em;

	@Autowired
	AnuncioRepository anuncioRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	StatusAnuncioRepository statusAnuncioRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	

	public Anuncio cadastrarAnuncio(Anuncio anuncio) throws EntidadeNaoEncontradaException {
		anuncio.setDataCriacao(LocalDateTime.now());
		anuncio.setDataExpiracao(LocalDateTime.now().plusDays(60));
		StatusAnuncio status = new StatusAnuncio();
		status.setIdStatus(StatusAnuncioEnum.EM_ANDAMENTO.getValor());
		anuncio.setStatus(status);
		return this.salvarAnuncio(anuncio);
	}
	
	public Anuncio salvarAnuncio(Anuncio anuncio) throws EntidadeNaoEncontradaException {
		verificaInformacoesValidasDoAnuncio(anuncio);
		anuncioRepository.save(anuncio);
		return this.buscarAnuncioPorId(anuncio.getIdAnuncio());
	}

	public Anuncio buscarAnuncioPorId(long idAnuncio) throws EntidadeNaoEncontradaException {

		return anuncioRepository.findByIdAnuncio(idAnuncio).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Anúncio com id %d não encontrado", idAnuncio)));
	}

	public List<Anuncio> buscarAnunciosPorUsuario(long idUsuario) throws EntidadeNaoEncontradaException {

		return anuncioRepository.findAllByIdUsuario(idUsuario).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Nenhum anúncio encontrado para o usuário %d", idUsuario)));
	}

	public double getAvaliacaoAnuncio(long idAnuncio) throws EntidadeNaoEncontradaException {
		Anuncio a = buscarAnuncioPorId(idAnuncio);

		return a.getNotaAvaliacao();
	}

	public Anuncio registraAvaliacaoAnuncio(AvaliacaoDto av) throws EntidadeNaoEncontradaException {
		Anuncio a = buscarAnuncioPorId(av.getIdAnuncio());
		a.setNotaAvaliacao(av.getNotaAvaliacao());
		return anuncioRepository.save(a);
	}

	public List<Anuncio> buscarAnunciosPaginacao(final int pagina, final int limite) {
		final List<Anuncio> anuncios = new ArrayList<Anuncio>();
		List<Anuncio> retorno = anuncioRepository
				.buscarAnuncioComPaginacao(StatusAnuncioEnum.EM_ANDAMENTO.getValor(), PageRequest.of(pagina, limite, Sort.Direction.DESC, "dataCriacao"));
		retorno.forEach(anuncio -> {
			anuncios.add(anuncio);
		});
		return anuncios;
	}

	public void deletarAnuncio(Anuncio anuncio) throws EntidadeNaoEncontradaException {
		buscarAnuncioPorId(anuncio.getIdAnuncio());
		anuncioRepository.delete(anuncio);
	}
	
	protected void verificaInformacoesValidasDoAnuncio(Anuncio anuncio) throws EntidadeNaoEncontradaException{
		
		if(anuncio.getTitulo().trim().isEmpty() || anuncio.getTitulo() == null) {
			throw new NullPointerException("O campo titulo não pode ser nulo ou vazio");
		}
		
		if(anuncio.getDescricao().trim().isEmpty() || anuncio.getTitulo() == null) {
			throw new NullPointerException("O campo descricao não pode ser nulo ou vazio");
		}
		
		if(anuncio.getCategoria().equals(null) || anuncio.getCategoria().getIdCategoria() == 0) {
			throw new NullPointerException("A categoria não pode ser nulo ou vazio");
		}
		
		if(anuncio.getStatus().equals(null) || anuncio.getStatus().getIdStatus() == 0 ) {
			throw new NullPointerException("O status não pode ser nulo ou vazio");
		}
		
		if(anuncio.getUsuarioAnunciante().equals(null) || anuncio.getUsuarioAnunciante().getIdUsuario() == 0 ) {
			throw new NullPointerException("O usuário não pode ser nulo ou vazio");
		}
		
		categoriaRepository.findByIdCategoria(anuncio.getCategoria().getIdCategoria()).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Categoria id %d inválida ou não encontrada", anuncio.getCategoria().getIdCategoria())));
		
		statusAnuncioRepository.findByIdStatus(anuncio.getStatus().getIdStatus()).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Status com id %d inválido ou não encontrado", anuncio.getStatus().getIdStatus())));
		
		usuarioRepository.findByIdUsuario(anuncio.getUsuarioAnunciante().getIdUsuario()).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Usuário com id %d inválido ou não encontrado", anuncio.getUsuarioAnunciante().getIdUsuario())));
		
	}
	
	public List<Anuncio> listaAnunciosPorTituloAnuncio(String titulo, final int pagina, final int limite) throws EntidadeNaoEncontradaException {
		titulo = "%" + titulo + "%";
		
		final List<Anuncio> anuncios = new ArrayList<Anuncio>();
		List<Anuncio> retorno = anuncioRepository
				.buscarAnuncioPorTituloComPaginacao(titulo, StatusAnuncioEnum.EM_ANDAMENTO.getValor(), PageRequest.of(pagina, limite, Sort.Direction.DESC, "data_criacao"));
		retorno.forEach(anuncio -> {
			anuncios.add(anuncio);
		});
		return anuncios;
	}
	
	public List<Anuncio> listaAnunciosPorCidadeAnuncio(String cidade, final int pagina, final int limite) throws EntidadeNaoEncontradaException {
		final List<Anuncio> anuncios = new ArrayList<Anuncio>();
		List<Anuncio> retorno = anuncioRepository
				.buscarAnuncioPorCidadeComPaginacao(cidade, StatusAnuncioEnum.EM_ANDAMENTO.getValor(), PageRequest.of(pagina, limite, Sort.Direction.DESC, "data_criacao"));
		retorno.forEach(anuncio -> {
			anuncios.add(anuncio);
		});
		return anuncios;
	}
	
	@Transactional
	public Anuncio alterarStatusAnuncio(StatusAnuncioDto statusAnuncioDto) throws EntidadeNaoEncontradaException {
		StatusAnuncio status = new StatusAnuncio();
		status.setIdStatus(statusAnuncioDto.getStatus().getValor());
		
		Anuncio anuncio = this.buscarAnuncioPorId(statusAnuncioDto.getIdAnuncio());
		anuncio.setStatus(status);
		
		return this.salvarAnuncio(anuncio);
	}
	
	public Anuncio cadastrarFotosAnuncio(AnuncioFotosType fotos) throws EntidadeNaoEncontradaException {
		Anuncio anuncio = anuncioRepository.findByIdAnuncio(fotos.getIdAnuncio()).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Anúncio com id %d não encontrado", fotos.getIdAnuncio())));
//		for (AnuncioFotos foto : fotos.getFotos()) {
//			logger.info(foto.getFoto());
//		}
		anuncio.setFotos(fotos.getFotos());
		for (AnuncioFotos foto : anuncio.getFotos()) {
			logger.info(foto.getFoto());
		}
		return anuncioRepository.save(anuncio);
		
	}
}
