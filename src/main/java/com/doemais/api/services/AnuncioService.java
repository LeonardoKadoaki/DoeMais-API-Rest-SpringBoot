package com.doemais.api.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.doemais.api.dto.DoadorDonatarioType;
import com.doemais.api.dto.InteressadosAnuncioType;
import com.doemais.api.dto.InteresseType;
import com.doemais.api.dto.StatusAnuncioDto;
import com.doemais.api.enums.InteresseEnum;
import com.doemais.api.enums.StatusAnuncioEnum;
import com.doemais.api.exception.ConflictException;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.models.Anuncio;
import com.doemais.api.models.AnuncioFotos;
import com.doemais.api.models.InteresseAnuncio;
import com.doemais.api.models.StatusAnuncio;
import com.doemais.api.repository.AnuncioRepository;
import com.doemais.api.repository.CategoriaRepository;
import com.doemais.api.repository.EnderecoRepository;
import com.doemais.api.repository.InteresseAnuncioRepository;
import com.doemais.api.repository.StatusAnuncioRepository;
import com.doemais.api.repository.UsuarioRepository;

@Service
public class AnuncioService {

	Logger logger = LoggerFactory.getLogger(AnuncioService.class);

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

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	InteresseAnuncioRepository interesseRepository;

	public Anuncio cadastrarAnuncio(Anuncio anuncio) throws EntidadeNaoEncontradaException, ConflictException {
		usuarioService.buscarUsuarioPorId(anuncio.getUsuarioAnunciante().getIdUsuario());
		
		enderecoRepository.findByUsuarioIdUsuario(anuncio.getUsuarioAnunciante().getIdUsuario())
				.orElseThrow(() -> new ConflictException(
						String.format("O usuário id %d precisa cadastrar um endereço antes de criar um anúncio",
								anuncio.getUsuarioAnunciante().getIdUsuario())));

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

	public Anuncio registraAvaliacaoAnuncio(AvaliacaoDto av) throws EntidadeNaoEncontradaException, ConflictException {
		Anuncio a = buscarAnuncioPorId(av.getIdAnuncio());
		usuarioService.buscarUsuarioPorId(av.getIdAvaliador());
		
		if (a.getNotaAvaliacao() != 0)
			throw new ConflictException("Anúncio já avaliado");

		if (a.getStatus().getIdStatus() != StatusAnuncioEnum.CONCLUIDO.valor) {
			throw new ConflictException(String.format("Anúncio %d não foi concluído!", a.getIdAnuncio()));
		}

		if (a.getIdDonatario() != av.getIdAvaliador()) {
			throw new ConflictException(String.format("O avaliador %d não é o donatário do anúncio!", av.getIdAvaliador()));
		}

		a.setNotaAvaliacao(av.getNotaAvaliacao());
		a.setIdAvaliador(av.getIdAvaliador());
		return anuncioRepository.save(a);
	}

	public List<Anuncio> buscarAnunciosPaginacao(final int pagina, final int limite) {
		final List<Anuncio> anuncios = new ArrayList<Anuncio>();
		List<Anuncio> retorno = anuncioRepository.buscarAnuncioComPaginacao(StatusAnuncioEnum.EM_ANDAMENTO.getValor(),
				PageRequest.of(pagina, limite, Sort.Direction.DESC, "dataCriacao"));
		retorno.forEach(anuncio -> {
			anuncios.add(anuncio);
		});
		return anuncios;
	}

	public void deletarAnuncio(long idAnuncio) throws EntidadeNaoEncontradaException {
		Anuncio anuncio = buscarAnuncioPorId(idAnuncio);
		anuncioRepository.delete(anuncio);
	}

	protected void verificaInformacoesValidasDoAnuncio(Anuncio anuncio) throws EntidadeNaoEncontradaException {

		if (anuncio.getTitulo().trim().isEmpty() || anuncio.getTitulo() == null) {
			throw new NullPointerException("O campo titulo não pode ser nulo ou vazio");
		}

		if (anuncio.getDescricao().trim().isEmpty() || anuncio.getTitulo() == null) {
			throw new NullPointerException("O campo descricao não pode ser nulo ou vazio");
		}

		if (anuncio.getCategoria().equals(null) || anuncio.getCategoria().getIdCategoria() == 0) {
			throw new NullPointerException("A categoria não pode ser nulo ou vazio");
		}

		if (anuncio.getStatus().equals(null) || anuncio.getStatus().getIdStatus() == 0) {
			throw new NullPointerException("O status não pode ser nulo ou vazio");
		}

		if (anuncio.getUsuarioAnunciante().equals(null) || anuncio.getUsuarioAnunciante().getIdUsuario() == 0) {
			throw new NullPointerException("O usuário não pode ser nulo ou vazio");
		}

		categoriaRepository.findByIdCategoria(anuncio.getCategoria().getIdCategoria()).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Categoria id %d inválida ou não encontrada",
						anuncio.getCategoria().getIdCategoria())));

		statusAnuncioRepository.findByIdStatus(anuncio.getStatus().getIdStatus())
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String
						.format("Status com id %d inválido ou não encontrado", anuncio.getStatus().getIdStatus())));

		usuarioRepository.findByIdUsuario(anuncio.getUsuarioAnunciante().getIdUsuario()).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Usuário com id %d inválido ou não encontrado",
						anuncio.getUsuarioAnunciante().getIdUsuario())));

	}

	public List<Anuncio> listaAnunciosPorTituloAnuncio(String titulo, final int pagina, final int limite)
			throws EntidadeNaoEncontradaException {
		titulo = "%" + titulo + "%";

		final List<Anuncio> anuncios = new ArrayList<Anuncio>();
		List<Anuncio> retorno = anuncioRepository.buscarAnuncioPorTituloComPaginacao(titulo,
				StatusAnuncioEnum.EM_ANDAMENTO.getValor(),
				PageRequest.of(pagina, limite, Sort.Direction.DESC, "data_criacao"));
		retorno.forEach(anuncio -> {
			anuncios.add(anuncio);
		});
		return anuncios;
	}

	public List<Anuncio> listaAnunciosPorCidadeAnuncio(String cidade, final int pagina, final int limite)
			throws EntidadeNaoEncontradaException {
		final List<Anuncio> anuncios = new ArrayList<Anuncio>();
		List<Anuncio> retorno = anuncioRepository.buscarAnuncioPorCidadeComPaginacao(cidade,
				StatusAnuncioEnum.EM_ANDAMENTO.getValor(),
				PageRequest.of(pagina, limite, Sort.Direction.DESC, "data_criacao"));
		retorno.forEach(anuncio -> {
			anuncios.add(anuncio);
		});
		return anuncios;
	}

	@Transactional
	public Anuncio alterarStatusAnuncio(StatusAnuncioDto statusAnuncioDto) throws EntidadeNaoEncontradaException, ConflictException {
		StatusAnuncio status = new StatusAnuncio();
		status.setIdStatus(statusAnuncioDto.getStatus().getValor());

		Anuncio anuncio = this.buscarAnuncioPorId(statusAnuncioDto.getIdAnuncio());
		
		if(anuncio.getStatus().getIdStatus() == StatusAnuncioEnum.CONCLUIDO.getValor()) {
			throw new ConflictException("Anúncio já concluído");
		}
		
		if (statusAnuncioDto.getStatus() == StatusAnuncioEnum.CONCLUIDO) {
			InteresseAnuncio interesse = interesseRepository
					.findByAnuncioIdAnuncio(statusAnuncioDto.getIdAnuncio(), statusAnuncioDto.getIdDonatario())
					.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(
							"Donatario interessado com id %d não encontrado", statusAnuncioDto.getIdDonatario())));
			anuncio.setStatus(status);
			anuncio.setIdDonatario(statusAnuncioDto.getIdDonatario());
			interesse.setStatus(InteresseEnum.CONCLUIDO);
		} else {
			anuncio = this.buscarAnuncioPorId(statusAnuncioDto.getIdAnuncio());
			anuncio.setStatus(status);
		}
		return this.salvarAnuncio(anuncio);
	}

	public Anuncio cadastrarFotosAnuncio(AnuncioFotosType fotos) throws EntidadeNaoEncontradaException {
		Anuncio anuncio = anuncioRepository.findByIdAnuncio(fotos.getIdAnuncio())
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format("Anúncio com id %d não encontrado", fotos.getIdAnuncio())));
//		for (AnuncioFotos foto : fotos.getFotos()) {
//			logger.info(foto.getFoto());
//		}
		anuncio.setFotos(fotos.getFotos());
		for (AnuncioFotos foto : anuncio.getFotos()) {
			logger.info(foto.getFoto());
		}
		return anuncioRepository.save(anuncio);
	}

	public DoadorDonatarioType doadorDonatario(long idAnuncio) throws EntidadeNaoEncontradaException {
		Anuncio a = this.buscarAnuncioPorId(idAnuncio);
		if (a.getStatus().getIdStatus() == StatusAnuncioEnum.CONCLUIDO.valor) {
			DoadorDonatarioType doador = new DoadorDonatarioType();
			doador.setIdDonatario(a.getIdDonatario());
			doador.setIdUsuario(a.getUsuarioAnunciante().getIdUsuario());
			return doador;
		}
		throw new EntidadeNaoEncontradaException(String.format("O anúncio id %d ainda não foi concluído", idAnuncio));
	}

	public void registrarInteresseAnuncio(InteresseType interesseType)
			throws EntidadeNaoEncontradaException, ConflictException {

		Anuncio anuncio = this.buscarAnuncioPorId(interesseType.getIdAnuncio());
		
		int countAnuncio = interesseRepository.countByUsuarioIdUsuario(interesseType.getIdUsuario(), interesseType.getIdAnuncio());
		
		if (anuncio.getStatus().getIdStatus() != StatusAnuncioEnum.EM_ANDAMENTO.valor) {
			throw new ConflictException(String.format("O anúncio não está mais disponível. Status atual: %s", anuncio.getStatus().getDescricaoStatus()));
		}
		
		if(countAnuncio > 0) {
			throw new ConflictException(String.format("Usuário id %d já interessado no anúncio", interesseType.getIdUsuario()));
		}
		
		long anunciante = anuncio.getUsuarioAnunciante().getIdUsuario();
		if (anunciante == interesseType.getIdUsuario()) {
			throw new ConflictException(String.format("O usuário %d não pode demonstrar interesse no próprio anúncio!", anunciante));
		}

		InteresseAnuncio interesse = new InteresseAnuncio();
		interesse.setUsuario(usuarioService.buscarUsuarioPorId(interesseType.getIdUsuario()));
		interesse.setAnuncio(anuncio);
		interesse.setDataRegistro(LocalDateTime.now());
		interesse.setStatus(InteresseEnum.INTERESSADO);

		interesseRepository.save(interesse);
	}

	public List<InteressadosAnuncioType> consultarInteressadosAnuncio(long idAnuncio) throws EntidadeNaoEncontradaException {
		this.buscarAnuncioPorId(idAnuncio);
		
		Optional<List<InteresseAnuncio>> listaInteressados = interesseRepository.findAllByAnuncioIdAnuncio(idAnuncio);
		
		if(!listaInteressados.isPresent()) {
			throw new EntidadeNaoEncontradaException(String.format("Não há interessados no anúncio %d", idAnuncio));
		}
		
		List<InteressadosAnuncioType> listaInteressadosType = new ArrayList<>();

		for (InteresseAnuncio interesseAnuncio : listaInteressados.get()) {
			InteressadosAnuncioType interessado = new InteressadosAnuncioType();
			interessado.setIdAnuncio(interesseAnuncio.getAnuncio().getIdAnuncio());
			interessado.setIdUsuarioInteressado(interesseAnuncio.getUsuario().getIdUsuario());
			interessado.setNome(interesseAnuncio.getUsuario().getNome());
			listaInteressadosType.add(interessado);

		}

		return listaInteressadosType;
	}

}
