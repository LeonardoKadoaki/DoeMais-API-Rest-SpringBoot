package com.doemais.api.services;

import com.doemais.api.dto.MessageObjectType;
import com.doemais.api.dto.PedidoDto;
import com.doemais.api.dto.StatusPedidoDto;
import com.doemais.api.enums.MessageEnum;
import com.doemais.api.enums.StatusAnuncioEnum;
import com.doemais.api.exception.ConflictException;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.exception.PedidoException;
import com.doemais.api.models.Pedido;
import com.doemais.api.models.StatusAnuncio;
import com.doemais.api.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

	private final Logger logger = LoggerFactory.getLogger(PedidoService.class);

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private StatusAnuncioRepository statusAnuncioRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Pedido> getAll() {
		return pedidoRepository.findAll();
	}

	public Pedido getById(long idPedido)
			throws EntidadeNaoEncontradaException {

		return pedidoRepository.findByIdPedido(idPedido).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Pedido com id %d não encontrado", idPedido)));
	}

	public MessageObjectType create(Pedido pedido)
			throws EntidadeNaoEncontradaException, ConflictException, PedidoException {

		long idUsuario = pedido.getSolicitante().getIdUsuario();
		usuarioService.buscarUsuarioPorId(idUsuario);
		enderecoRepository.findByUsuarioIdUsuario(idUsuario).orElseThrow(
				() -> new ConflictException(
						String.format("O usuário id %d precisa cadastrar um endereço antes de criar um pedido", idUsuario)));

		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setDataExpiracao(LocalDateTime.now().plusDays(60));

		StatusAnuncio status = new StatusAnuncio();
		status.setIdStatus(StatusAnuncioEnum.EM_ANDAMENTO.getValor());
		pedido.setStatus(status);

		if (this.save(pedido) == null) {
			logger.error("Erro ao criar o pedido!");
			throw new PedidoException("Erro ao criar o pedido!");
		}
		
		logger.info("Pedido criado!");
		return new MessageObjectType("Pedido criado!", MessageEnum.PEDIDO_CRIADO);
	}

	public Pedido update(long idPedido, PedidoDto pedido)
			throws EntidadeNaoEncontradaException {

		Pedido entity = this.getById(idPedido);
		entity.setTitulo(pedido.getTitulo());
		entity.setDescricao(pedido.getDescricao());
		entity.setCategoria(pedido.getCategoria());

		return this.save(entity);
	}

	public void delete(long idPedido)
			throws EntidadeNaoEncontradaException {

		Pedido pedido = this.getById(idPedido);
		pedidoRepository.delete(pedido);
	}

	@Transactional
	public Pedido alterarStatus(StatusPedidoDto status)
			throws EntidadeNaoEncontradaException, ConflictException {

		StatusAnuncio st = new StatusAnuncio();
		st.setIdStatus(status.getStatus().getValor());

		Pedido pedido = this.getById(status.getIdPedido());
		if (pedido.getStatus().getIdStatus() == StatusAnuncioEnum.CONCLUIDO.getValor()) {
			throw new ConflictException("Pedido já concluído");
		}

		// Se atualizou para concluido...
		if (status.getStatus() == StatusAnuncioEnum.CONCLUIDO) {

			usuarioService.buscarUsuarioPorId(status.getIdDoador());
			long solicitante = pedido.getSolicitante().getIdUsuario();

			if (solicitante == status.getIdDoador()) {
				throw new ConflictException(String.format("O usuário %d não pode doar para o próprio pedido!", solicitante));
			}

			pedido.setDataFim(LocalDateTime.now());
			pedido.setIdDoador(status.getIdDoador());
			logger.info("Pedido concluído. ID: " + pedido.getIdPedido());
		}

		pedido.setStatus(st); // Atualiza status...
		return this.save(pedido);
	}

	public List<Pedido> getByIdUsuario(long idUsuario)
			throws EntidadeNaoEncontradaException {

		return pedidoRepository.findAllByIdUsuario(idUsuario).orElseThrow(
				() -> new EntidadeNaoEncontradaException(
						String.format("Nenhum pedido encontrado para o usuário %d", idUsuario)));
	}

	private Pedido save(Pedido pedido)
			throws EntidadeNaoEncontradaException {

		verificaDados(pedido);
		pedidoRepository.save(pedido);

		return this.getById(pedido.getIdPedido());
	}

	private void verificaDados(Pedido pedido)
			throws EntidadeNaoEncontradaException {

		if (pedido.getTitulo() == null || pedido.getTitulo().trim().isEmpty()) {
			throw new IllegalArgumentException("O campo titulo não pode ser nulo ou vazio");
		}

		if (pedido.getDescricao() == null || pedido.getDescricao().trim().isEmpty()) {
			throw new IllegalArgumentException("O campo descricao não pode ser nulo ou vazio");
		}

		if (pedido.getCategoria() == null || pedido.getCategoria().getIdCategoria() == 0) {
			throw new IllegalArgumentException("A categoria não pode ser nulo ou vazio");
		}

		if (pedido.getStatus() == null || pedido.getStatus().getIdStatus() == 0) {
			throw new IllegalArgumentException("O status não pode ser nulo ou vazio");
		}

		if (pedido.getSolicitante() == null || pedido.getSolicitante().getIdUsuario() == 0) {
			throw new IllegalArgumentException("O usuário não pode ser nulo ou vazio");
		}

		categoriaRepository.findByIdCategoria(pedido.getCategoria().getIdCategoria()).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Categoria id %d inválida ou não encontrada",
						pedido.getCategoria().getIdCategoria())));

		statusAnuncioRepository.findByIdStatus(pedido.getStatus().getIdStatus()).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Status com id %d inválido ou não encontrado",
						pedido.getStatus().getIdStatus())));

		usuarioRepository.findByIdUsuario(pedido.getSolicitante().getIdUsuario()).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Usuário com id %d inválido ou não encontrado",
						pedido.getSolicitante().getIdUsuario())));
	}
}
