package com.doemais.api.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.doemais.api.enums.MedalhaEnum;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.exception.MedalhaException;
import com.doemais.api.models.Anuncio;
import com.doemais.api.models.Medalha;
import com.doemais.api.models.Usuario;
import com.doemais.api.models.UsuarioMedalha;
import com.doemais.api.repository.MedalhaRepository;
import com.doemais.api.repository.UsuarioMedalhaRepository;

@Service
public class MedalhaService {

	Logger logger = LoggerFactory.getLogger(MedalhaService.class);

	@PersistenceContext
	private EntityManager em;

	@Autowired
	MedalhaRepository medalhaRepository;

	@Autowired
	UsuarioMedalhaRepository usuarioMedalhaRepository;

	public List<Medalha> consultarMedalhas() {
		return medalhaRepository.findAll();
	}

	public Medalha cadastrarMedalha(Medalha medalha) {
		return medalhaRepository.save(medalha);
	}
	
	public List<UsuarioMedalha> buscarMedalhasPorUsuario(long idUsuario) throws EntidadeNaoEncontradaException {

		return usuarioMedalhaRepository.findAllByIdUsuario(idUsuario).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Nenhuma medalha encontrada para o usuário %d", idUsuario)));
	}
	
	
	public void AdicionaMedalha(Usuario usuario, MedalhaEnum medalhaEnum)
			throws EntidadeNaoEncontradaException, MedalhaException {

		long idMedalha = medalhaEnum.getIdMedalha();

		Medalha medalha = medalhaRepository.findById(idMedalha).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Medalha com id %d não encontrada", idMedalha)));

		UsuarioMedalha userMedalha = usuarioMedalhaRepository
				.save(new UsuarioMedalha(LocalDateTime.now(), usuario, medalha));

		if (userMedalha != null) {
			logger.info(String.format("Medalha Obtida", medalhaEnum.getNomeMedalha(),
					medalhaEnum.getDescricaoMedalha(), usuario.getIdUsuario()));
		} else {
			logger.error(String.format("Erro ao obter medalha", medalhaEnum.getNomeMedalha(),
					medalhaEnum.getDescricaoMedalha(), usuario.getIdUsuario()));
			throw new MedalhaException(String.format("Erro ao obter medalha",
					medalhaEnum.getNomeMedalha(), medalhaEnum.getDescricaoMedalha(), usuario.getIdUsuario()));
		}
	}

	public boolean isUsuarioMedalha(long idUsuario, MedalhaEnum medalhas) {
		return usuarioMedalhaRepository.verificaUsuarioMedalha(idUsuario, medalhas.getIdMedalha()) > 0;
	}
}
