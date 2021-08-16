package com.doemais.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.doemais.api.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doemais.api.enums.MissaoEnum;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.exception.MissaoException;
import com.doemais.api.exception.MoedasException;
import com.doemais.api.repository.EstatisticasUsuarioRepository;
import com.doemais.api.repository.MissaoRealizadaUsuarioRepository;
import com.doemais.api.repository.MissaoRepository;

@Service
public class MissaoService {

	Logger logger = LoggerFactory.getLogger(MissaoService.class);

	@PersistenceContext
	private EntityManager em;

	@Autowired
	MissaoRepository missaoRepository;

	@Autowired
	MissaoRealizadaUsuarioRepository missaoRealizadaUsuarioRepository;

	@Autowired
	EstatisticasUsuarioRepository estatisiticasUsuarioRepository;

	public Missao cadastrarMissao(Missao missao) {
		return missaoRepository.save(missao);
	}

	public List<Missao> consultarMissoes() {
		return missaoRepository.findAll();
	}

	public void completaMissao(Usuario usuario, MissaoEnum missao)
			throws EntidadeNaoEncontradaException, MissaoException, MoedasException {
		long idMissao = missao.getId();

		// apenas para confirmar que a missão está cadastrada no banco
		Missao missaoAux = missaoRepository.findById(idMissao).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Missão com id %d não encontrada", idMissao)));

		MissaoRealizadaUsuario missaoRel = missaoRealizadaUsuarioRepository
				.save(new MissaoRealizadaUsuario(LocalDateTime.now(), usuario, missaoAux));

		if (missaoRel != null) {
			logger.info(String.format("Missão concluída [%s]. ID usuário: %d", missao.getDescricao(),
					usuario.getIdUsuario()));
		} else {
			logger.error(String.format("Erro ao concluir missão [%s]. ID usuário: %d", missao.getDescricao(),
					usuario.getIdUsuario()));
			throw new MissaoException(String.format("Erro ao concluir missão [%s]. ID usuário: %d",
					missao.getDescricao(), usuario.getIdUsuario()));
		}

		adicionaMoedas(missao.getMoedas(), usuario);
	}

	public EstatisticasUsuario adicionaMoedas(long moedas, Usuario usuario) throws MoedasException {

		Optional<EstatisticasUsuario> estatisticasUsuario = estatisiticasUsuarioRepository
				.findByUsuarioIdUsuario(usuario.getIdUsuario());

		long totalMoedasAntesDeAtualizar;

		if (!estatisticasUsuario.isPresent()) {
			EstatisticasUsuario novaEstatisticasUsuario = new EstatisticasUsuario();
			novaEstatisticasUsuario.setUsuario(usuario);
			novaEstatisticasUsuario
					.setTotalMoedas(calculaTotalMoedas(novaEstatisticasUsuario.getTotalMoedas(), moedas));
			logger.info(
					"Não há estatísticas para o usuário ID " + usuario.getIdUsuario() + ". Gerando novo registro...");
			return estatisiticasUsuarioRepository.save(novaEstatisticasUsuario);
		} else {
			totalMoedasAntesDeAtualizar = estatisticasUsuario.get().getTotalMoedas();
		}
		estatisticasUsuario.get()
				.setTotalMoedas(calculaTotalMoedas(estatisticasUsuario.get().getTotalMoedas(), moedas));
		logger.info("Adicionando moedas às estatísticas do usuario ID: " + usuario.getIdUsuario());

		EstatisticasUsuario eu = estatisiticasUsuarioRepository.save(estatisticasUsuario.get());

		if (eu.getTotalMoedas() == calculaTotalMoedas(totalMoedasAntesDeAtualizar, moedas)) {
			logger.info("Moedas adicionadas com sucesso!");
		} else {
			logger.error("Erro ao adicionar moedas!");
			throw new MoedasException("Erro ao adicionar moedas");
		}
		return eu;
	}

	private long calculaTotalMoedas(long totalMoedas, long moedasParaAdicionar) {

		return totalMoedas + moedasParaAdicionar;
	}

	public boolean isMissaoRealizada(long idUsuario, MissaoEnum m) {
		return missaoRealizadaUsuarioRepository.verificaMissaoRealizadaUsuario(idUsuario, m.getId()) > 0;
	}
}
