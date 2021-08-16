package com.doemais.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doemais.api.models.ReputacaoUsuario;

public interface ReputacaoRepository extends JpaRepository<ReputacaoUsuario, Long>{
	ReputacaoUsuario findByIdAvaliacao(long id);

	@Query(value = "select avg(notaAvaliacao) from reputacao_usuario where idUsuario = ?", nativeQuery = true)
	double mediaReputacaoByIdUsuario(long id);

	@Query(value = "select count(*) from reputacao_usuario where idUsuario = ?", nativeQuery = true)
	int totalAvaliacoesByIdUsuario(long id);

	@Query(value = "select count(*) from reputacao_usuario where idUsuario = ? and idAvaliador = ?", nativeQuery = true)
	int verificaAvaliacao(long idUsuario, long idAvaliador);
}
