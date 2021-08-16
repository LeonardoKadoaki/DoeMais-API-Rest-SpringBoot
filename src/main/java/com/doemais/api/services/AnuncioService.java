package com.doemais.api.services;

import static com.doemais.api.utils.Utilidades.forkAnuncio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.doemais.api.dto.*;
import com.doemais.api.enums.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.doemais.api.exception.AnuncioException;
import com.doemais.api.exception.ConflictException;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.exception.MedalhaException;
import com.doemais.api.exception.MissaoException;
import com.doemais.api.exception.MoedasException;
import com.doemais.api.models.Anuncio;
import com.doemais.api.models.AnuncioFotos;
import com.doemais.api.models.ResumoReputacaoUsuario;
import com.doemais.api.models.StatusAnuncio;
import com.doemais.api.models.Usuario;
import com.doemais.api.models.UsuarioInteressadoAnuncio;
import com.doemais.api.repository.AnuncioRepository;
import com.doemais.api.repository.CategoriaRepository;
import com.doemais.api.repository.EnderecoRepository;
import com.doemais.api.repository.InteresseAnuncioRepository;
import com.doemais.api.repository.ResumoReputacaoRepository;
import com.doemais.api.repository.StatusAnuncioRepository;
import com.doemais.api.repository.UsuarioRepository;
import com.doemais.api.search.AnuncioSearch;

@Service
public class AnuncioService {

	Logger logger = LoggerFactory.getLogger(AnuncioService.class);

	@PersistenceContext
	private EntityManager em;

	@Autowired
	AnuncioRepository anuncioRepository;

	@Autowired
	AnuncioSearch anuncioSearch;

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

	@Autowired
	MissaoService missaoService;
	
    @Autowired
    MedalhaService medalhaService;

	@Autowired
	ResumoReputacaoRepository resumoReputacaoRepository;

	public MessageObjectType criarAnuncio(Anuncio anuncio, boolean first) throws EntidadeNaoEncontradaException, ConflictException, MissaoException, MoedasException, AnuncioException {
		Anuncio anuncioCadastrado = this.cadastrarAnuncio(anuncio, first);
		
		if(anuncioCadastrado == null) {
			logger.error("Erro ao criar o anúncio!");
			throw new AnuncioException("Erro ao criar o anúncio!");
		}
		
		logger.info("Anúncio criado!");
		return new MessageObjectType("Anúncio criado!", MessageEnum.ANUNCIO_CRIADO);
		
	}
	
	public Anuncio cadastrarAnuncio(Anuncio anuncio, boolean first)
			throws EntidadeNaoEncontradaException, ConflictException, MissaoException, MoedasException {
		usuarioService.buscarUsuarioPorId(anuncio.getUsuarioAnunciante().getIdUsuario());

		enderecoRepository.findByUsuarioIdUsuario(anuncio.getUsuarioAnunciante().getIdUsuario())
				.orElseThrow(() -> new ConflictException(
						String.format("O usuário id %d precisa cadastrar um endereço antes de criar um anúncio",
								anuncio.getUsuarioAnunciante().getIdUsuario())));

		if (first) {
			anuncio.setNextIdAnuncio(0); // Primeiro item/anuncio nao aponta pra ninguém
			anuncio.setDataCriacao(LocalDateTime.now());
			anuncio.setDataExpiracao(LocalDateTime.now().plusDays(60));
			StatusAnuncio status = new StatusAnuncio();
			status.setIdStatus(StatusAnuncioEnum.EM_ANDAMENTO.getValor());
			anuncio.setStatus(status);
		}

		return this.salvarAnuncio(anuncio);
	
	}

	public Anuncio salvarAnuncio(Anuncio anuncio)
			throws EntidadeNaoEncontradaException, MissaoException, MoedasException {
		verificaInformacoesValidasDoAnuncio(anuncio);

		anuncioRepository.save(anuncio);

		return this.buscarAnuncioPorId(anuncio.getIdAnuncio());
	}

	public Anuncio buscarAnuncioPorId(long idAnuncio) throws EntidadeNaoEncontradaException {

		Anuncio a = anuncioRepository.findByIdAnuncio(idAnuncio).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Anúncio com id %d não encontrado", idAnuncio)));

		AvaliacaoType av = null;
		try {
			av = usuarioService.getAvaliacaoUsuario(a.getUsuarioAnunciante().getIdUsuario());
		} catch (EntidadeNaoEncontradaException e) {
			logger.info(String.format("Usuário com id %d ainda não foi avaliado", a.getUsuarioAnunciante().getIdUsuario()));
		}

		a.setCategoriaUsuarioAnunciante(av == null ? CategoriaDoadorEnum.NONE.getNome() : av.getCategoria());
		return a;
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
			throw new ConflictException(
					String.format("O avaliador %d não é o donatário do anúncio!", av.getIdAvaliador()));
		}

		a.setNotaAvaliacao(av.getNotaAvaliacao());
		a.setIdAvaliador(av.getIdAvaliador());
		return anuncioRepository.save(a);
	}

	public List<Anuncio> buscarAnunciosPaginacao(final int pagina, final int limite) {
		return anuncioRepository.buscarAnuncioComPaginacao(StatusAnuncioEnum.EM_ANDAMENTO.getValor(),
				PageRequest.of(pagina, limite, Sort.Direction.DESC, "dataCriacao"));
	}

	public void deletarAnuncio(long idAnuncio) throws EntidadeNaoEncontradaException {
		Anuncio anuncio = buscarAnuncioPorId(idAnuncio);
		anuncioRepository.delete(anuncio);
	}

	protected void verificaInformacoesValidasDoAnuncio(Anuncio anuncio) throws EntidadeNaoEncontradaException {

		if (anuncio.getTitulo() == null || anuncio.getTitulo().trim().isEmpty()) {
			throw new IllegalArgumentException("O campo titulo não pode ser nulo ou vazio");
		}

		if (anuncio.getDescricao() == null || anuncio.getDescricao().trim().isEmpty()) {
			throw new IllegalArgumentException("O campo descricao não pode ser nulo ou vazio");
		}

		if (anuncio.getQtdeItens() < 1) {
			throw new IllegalArgumentException("Pelo menos um item precisa ser doado");
		}

		if (anuncio.getCategoria() == null || anuncio.getCategoria().getIdCategoria() == 0) {
			throw new IllegalArgumentException("A categoria não pode ser nulo ou vazio");
		}

		if (anuncio.getStatus() == null || anuncio.getStatus().getIdStatus() == 0) {
			throw new IllegalArgumentException("O status não pode ser nulo ou vazio");
		}

		if (anuncio.getUsuarioAnunciante() == null || anuncio.getUsuarioAnunciante().getIdUsuario() == 0) {
			throw new IllegalArgumentException("O usuário não pode ser nulo ou vazio");
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

	public List<Anuncio> listarAnunciosPorPalavrasChave(String texto, final int pagina, final int limite)
			throws EntidadeNaoEncontradaException {
		return anuncioSearch.search(texto, pagina, limite);
	}

//	public List<Anuncio> listaAnunciosPorTituloAnuncio(String titulo, final int pagina, final int limite)
//			throws EntidadeNaoEncontradaException {
//		titulo = "%" + titulo + "%";
//
//		final List<Anuncio> anuncios = new ArrayList<Anuncio>();
//		List<Anuncio> retorno = anuncioRepository.buscarAnuncioPorTituloComPaginacao(titulo,
//				StatusAnuncioEnum.EM_ANDAMENTO.getValor(),
//				PageRequest.of(pagina, limite, Sort.Direction.DESC, "dataCriacao"));
//		retorno.forEach(anuncio -> {
//			anuncios.add(anuncio);
//		});
//		return anuncios;
//	}

	public List<Anuncio> listaAnunciosPorCidadeAnuncio(String cidade, final int pagina, final int limite)
			throws EntidadeNaoEncontradaException {
		final List<Anuncio> anuncios = new ArrayList<Anuncio>();
		List<Anuncio> retorno = anuncioRepository.buscarAnuncioPorCidadeComPaginacao(cidade,
				StatusAnuncioEnum.EM_ANDAMENTO.getValor(),
				PageRequest.of(pagina, limite, Sort.Direction.DESC, "dataCriacao"));
		retorno.forEach(anuncio -> {
			anuncios.add(anuncio);
		});
		return anuncios;
	}

	@Transactional
	public List<MessageMoedasObjectType> alterarStatusAnuncio(StatusAnuncioDto statusAnuncioDto)
			throws EntidadeNaoEncontradaException, ConflictException, MoedasException, MissaoException, MedalhaException {
		
		List<MessageMoedasObjectType> mensagensDeRetorno = new ArrayList<MessageMoedasObjectType>();
		
		StatusAnuncio status = new StatusAnuncio();
		status.setIdStatus(statusAnuncioDto.getStatus().getValor());

		Anuncio anuncio = this.buscarAnuncioPorId(statusAnuncioDto.getIdAnuncio());

		if (anuncio.getStatus().getIdStatus() == StatusAnuncioEnum.CONCLUIDO.getValor()) {
			throw new ConflictException("Anúncio já concluído");
		}

		if (statusAnuncioDto.getStatus() == StatusAnuncioEnum.CONCLUIDO) {
			UsuarioInteressadoAnuncio interesse = interesseRepository
					.findByAnuncioIdAnuncio(statusAnuncioDto.getIdAnuncio(), statusAnuncioDto.getIdDonatario())
					.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(
							"Donatário interessado com id %d não encontrado", statusAnuncioDto.getIdDonatario())));
			anuncio.setStatus(status);
			anuncio.setIdDonatario(statusAnuncioDto.getIdDonatario());
			anuncio.setDataFim(LocalDateTime.now());
			interesse.setStatus(InteresseEnum.CONCLUIDO);

			logger.info("Anuncio concluido. ID: " + anuncio.getIdAnuncio());
			// adiciona as moedas do anuncio ao total do usuario
			missaoService.adicionaMoedas(anuncio.getCategoria().getMoedasCategoria(), anuncio.getUsuarioAnunciante());
			
			mensagensDeRetorno = verificaGamificacao(anuncio);
			mensagensDeRetorno.add(new MessageMoedasObjectType("Anúncio concluído!", MessageEnum.ANUNCIO_CONCLUIDO, anuncio.getCategoria().getMoedasCategoria()));
			
			// Se ainda houver itens para doar, "recria" o anuncio...
			if (anuncio.getQtdeItens() > 1) {
				Anuncio restantes = forkAnuncio(anuncio);
				long nextIdAnuncio = this.cadastrarAnuncio(restantes, false).getIdAnuncio();
				anuncio.setNextIdAnuncio(nextIdAnuncio); // Id do novo item/anuncio
			}
		} else {
			anuncio = this.buscarAnuncioPorId(statusAnuncioDto.getIdAnuncio()); // Redundante
			anuncio.setStatus(status);
		}

		this.salvarAnuncio(anuncio);
		return mensagensDeRetorno;
	}

	private List<MessageMoedasObjectType> verificaGamificacao(Anuncio anuncio)
			throws EntidadeNaoEncontradaException, MissaoException, MoedasException, MedalhaException {

		Usuario usuario = anuncio.getUsuarioAnunciante();
		List<MessageMoedasObjectType> mensagens = new ArrayList<MessageMoedasObjectType>();

		// verificando se é o primeiro anuncio do usuario para completar a missão do primeiro anuncio
		if (this.primeiroAnuncio(anuncio)
				&& !missaoService.isMissaoRealizada(usuario.getIdUsuario(), MissaoEnum.PRIMEIRA_DOACAO) 
				&& !medalhaService.isUsuarioMedalha(usuario.getIdUsuario(), MedalhaEnum.PRIMEIRA_DOACAO)) {
			missaoService.completaMissao(usuario, MissaoEnum.PRIMEIRA_DOACAO);
			medalhaService.AdicionaMedalha(usuario, MedalhaEnum.PRIMEIRA_DOACAO);
			mensagens.add(new MessageMoedasObjectType("Primeira doação!", MessageEnum.MISSAO_CONCLUIDA, MissaoEnum.PRIMEIRA_DOACAO.getMoedas()));
		}

		// verificando se tem 5 anuncios pra concluir a missao dos 5 anuncios
		if (this.quantidadeDeAnuncios(anuncio) == 5
				&& !missaoService.isMissaoRealizada(usuario.getIdUsuario(), MissaoEnum.CINCO_DOACOES)
				&& !medalhaService.isUsuarioMedalha(usuario.getIdUsuario(), MedalhaEnum.CINCO_DOACOES)) {
			missaoService.completaMissao(usuario, MissaoEnum.CINCO_DOACOES);
			medalhaService.AdicionaMedalha(usuario, MedalhaEnum.CINCO_DOACOES);
			mensagens.add(new MessageMoedasObjectType("Conclua 5 doações!", MessageEnum.MISSAO_CONCLUIDA, MissaoEnum.CINCO_DOACOES.getMoedas()));
		}

		// verificando se tem 100 anuncios pra concluir a missao dos 100 anuncios
		if (this.quantidadeDeAnuncios(anuncio) == 100
				&& !missaoService.isMissaoRealizada(usuario.getIdUsuario(), MissaoEnum.CEM_DOACOES)
				&& !medalhaService.isUsuarioMedalha(usuario.getIdUsuario(), MedalhaEnum.CEM_DOACOES)) {
			missaoService.completaMissao(usuario, MissaoEnum.CEM_DOACOES);
			medalhaService.AdicionaMedalha(usuario, MedalhaEnum.CEM_DOACOES);
			mensagens.add(new MessageMoedasObjectType("Conclua 100 doações!", MessageEnum.MISSAO_CONCLUIDA, MissaoEnum.CEM_DOACOES.getMoedas()));
		}

		// verificando se reputacao 4 estrelas pra concluir missao 4 estrelas
		if (this.possuiReputacaoQuatroEstrelas(anuncio)
				&& !missaoService.isMissaoRealizada(usuario.getIdUsuario(), MissaoEnum.DOADOR_QUATRO_ESTRELAS)
				&& !medalhaService.isUsuarioMedalha(usuario.getIdUsuario(), MedalhaEnum.DOADOR_QUATRO_ESTRELAS)) {
			missaoService.completaMissao(usuario, MissaoEnum.DOADOR_QUATRO_ESTRELAS);
			medalhaService.AdicionaMedalha(usuario, MedalhaEnum.DOADOR_QUATRO_ESTRELAS);
			mensagens.add(new MessageMoedasObjectType("Seja um doador 4 estrelas!", MessageEnum.MISSAO_CONCLUIDA,
					MissaoEnum.DOADOR_QUATRO_ESTRELAS.getMoedas()));
		}

		// verificando se reputacao 5 estrelas pra concluir missao 5 estrelas
		if (this.possuiReputacaoCincoEstrelas(anuncio)
				&& !missaoService.isMissaoRealizada(usuario.getIdUsuario(), MissaoEnum.DOADOR_CINCO_ESTRELAS)
				&& !medalhaService.isUsuarioMedalha(usuario.getIdUsuario(), MedalhaEnum.DOADOR_CINCO_ESTRELAS)) {
			missaoService.completaMissao(usuario, MissaoEnum.DOADOR_CINCO_ESTRELAS);
			medalhaService.AdicionaMedalha(usuario, MedalhaEnum.DOADOR_CINCO_ESTRELAS);
			mensagens.add(new MessageMoedasObjectType("Seja um doador 5 estrelas!", MessageEnum.MISSAO_CONCLUIDA,
					MissaoEnum.DOADOR_CINCO_ESTRELAS.getMoedas()));
		}

		// verificando se possui tres anuncios no mes para completar a missão dos tres anuncios
		if (this.possuiTresAnunciosEmUmMes(anuncio)
				&& !missaoService.isMissaoRealizada(usuario.getIdUsuario(), MissaoEnum.TRES_DOACOES_EM_UM_MES)
				&& !medalhaService.isUsuarioMedalha(usuario.getIdUsuario(), MedalhaEnum.TRES_DOACOES_EM_UM_MES)) {
			missaoService.completaMissao(usuario, MissaoEnum.TRES_DOACOES_EM_UM_MES);
			medalhaService.AdicionaMedalha(usuario, MedalhaEnum.TRES_DOACOES_EM_UM_MES);
			mensagens.add(new MessageMoedasObjectType("Conclua 3 doações no mesmo mês!", MessageEnum.MISSAO_CONCLUIDA,
					MissaoEnum.TRES_DOACOES_EM_UM_MES.getMoedas()));
		}
		
		return mensagens;
	}

	private boolean possuiTresAnunciosEmUmMes(Anuncio anuncio) throws EntidadeNaoEncontradaException {

		List<Anuncio> anuncios = anuncioRepository.findAllByIdUsuario(anuncio.getUsuarioAnunciante().getIdUsuario())
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(
						"Nenhum anúncio encontrado. Usuário ID: %d", anuncio.getUsuarioAnunciante().getIdUsuario())));

		List<Anuncio> anunciosDoMes = anuncios.stream()
				.filter(a -> a.getDataCriacao().getMonth() == LocalDateTime.now().getMonth())
				.collect(Collectors.toList());

		return anunciosDoMes.size() >= 3;
	}

	private boolean possuiReputacaoQuatroEstrelas(Anuncio anuncio) {

		long idUsuario = anuncio.getUsuarioAnunciante().getIdUsuario();

		Optional<ResumoReputacaoUsuario> resumoReputacao = resumoReputacaoRepository.findByUsuarioIdUsuario(idUsuario);

		int qtdAnunciosQuatro = anuncioRepository.quantidadeDeAnunciosQuatroEstrelasMais(idUsuario);

		return resumoReputacao.isPresent() && resumoReputacao.get().getNotaAvaliacao() >= 4 && qtdAnunciosQuatro >= 10;
	}

	private boolean possuiReputacaoCincoEstrelas(Anuncio anuncio) {

		long idUsuario = anuncio.getUsuarioAnunciante().getIdUsuario();

		Optional<ResumoReputacaoUsuario> resumoReputacao = resumoReputacaoRepository.findByUsuarioIdUsuario(idUsuario);

		int qtdAnunciosCinco = anuncioRepository.quantidadeDeAnunciosCincoEstrelas(idUsuario);

		return resumoReputacao.isPresent() && resumoReputacao.get().getNotaAvaliacao() == 5 && qtdAnunciosCinco >= 10;
	}

	private int quantidadeDeAnuncios(Anuncio anuncio) {
		return anuncioRepository.quantidadeDeAnunciosDoUsuario(anuncio.getUsuarioAnunciante().getIdUsuario(),
				StatusAnuncioEnum.CONCLUIDO.getValor());
	}

	private boolean primeiroAnuncio(Anuncio anuncio) {
		return this.quantidadeDeAnuncios(anuncio) == 1;
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

	public DoadorType doador(long idAnuncio) throws EntidadeNaoEncontradaException {
		Anuncio a = this.buscarAnuncioPorId(idAnuncio);
		if (a.getStatus().getIdStatus() == StatusAnuncioEnum.CONCLUIDO.valor) {
			DoadorType doador = new DoadorType();
			doador.setIdUsuario(a.getUsuarioAnunciante().getIdUsuario());
			return doador;
		}
		throw new EntidadeNaoEncontradaException(String.format("O anúncio id %d ainda não foi concluído", idAnuncio));
	}

	public DonatarioType donatario(long idAnuncio) throws EntidadeNaoEncontradaException {
		Anuncio a = this.buscarAnuncioPorId(idAnuncio);
		if (a.getStatus().getIdStatus() == StatusAnuncioEnum.CONCLUIDO.valor) {
			DonatarioType donatario = new DonatarioType();
			donatario.setIdDonatario(a.getIdDonatario());
			return donatario;
		}
		throw new EntidadeNaoEncontradaException(String.format("O anúncio id %d ainda não foi concluído", idAnuncio));
	}

	public void registrarInteresseAnuncio(InteresseType interesseType)
			throws EntidadeNaoEncontradaException, ConflictException {

		Anuncio anuncio = this.buscarAnuncioPorId(interesseType.getIdAnuncio());

		int countAnuncio = interesseRepository.countByUsuarioIdUsuario(interesseType.getIdUsuario(),
				interesseType.getIdAnuncio());

		if (anuncio.getStatus().getIdStatus() != StatusAnuncioEnum.EM_ANDAMENTO.valor) {
			throw new ConflictException(String.format("O anúncio não está mais disponível. Status atual: %s",
					anuncio.getStatus().getDescricaoStatus()));
		}

		if (countAnuncio > 0) {
			throw new ConflictException(
					String.format("Usuário id %d já interessado no anúncio", interesseType.getIdUsuario()));
		}

		long anunciante = anuncio.getUsuarioAnunciante().getIdUsuario();
		if (anunciante == interesseType.getIdUsuario()) {
			throw new ConflictException(
					String.format("O usuário %d não pode demonstrar interesse no próprio anúncio!", anunciante));
		}

		UsuarioInteressadoAnuncio interesse = new UsuarioInteressadoAnuncio();
		interesse.setUsuario(usuarioService.buscarUsuarioPorId(interesseType.getIdUsuario()));
		interesse.setAnuncio(anuncio);
		interesse.setDataRegistro(LocalDateTime.now());
		interesse.setStatus(InteresseEnum.INTERESSADO);

		interesseRepository.save(interesse);
	}

	public List<InteressadosAnuncioType> consultarInteressadosAnuncio(long idAnuncio)
			throws EntidadeNaoEncontradaException {
		this.buscarAnuncioPorId(idAnuncio);

		Optional<List<UsuarioInteressadoAnuncio>> listaInteressados = interesseRepository
				.findAllByAnuncioIdAnuncio(idAnuncio);

		if (!listaInteressados.isPresent()) {
			throw new EntidadeNaoEncontradaException(String.format("Não há interessados no anúncio %d", idAnuncio));
		}

		List<InteressadosAnuncioType> listaInteressadosType = new ArrayList<>();

		for (UsuarioInteressadoAnuncio interesseAnuncio : listaInteressados.get()) {
			InteressadosAnuncioType interessado = new InteressadosAnuncioType();
			interessado.setIdAnuncio(interesseAnuncio.getAnuncio().getIdAnuncio());
			interessado.setIdUsuarioInteressado(interesseAnuncio.getUsuario().getIdUsuario());
			interessado.setNome(interesseAnuncio.getUsuario().getNome());
			listaInteressadosType.add(interessado);

		}

		return listaInteressadosType;
	}

	public ItensRestantesType getItensRestantes(long idAnuncio) throws EntidadeNaoEncontradaException {

		Anuncio a = this.buscarAnuncioPorId(idAnuncio);
		ItensRestantesType ir = new ItensRestantesType();
		ir.setItensRestantes(a.getQtdeItens());
		ir.setNextIdAnuncio(a.getNextIdAnuncio());
		return ir;
	}
}
