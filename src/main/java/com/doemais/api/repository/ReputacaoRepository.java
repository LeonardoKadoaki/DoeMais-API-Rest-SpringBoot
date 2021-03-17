package com.doemais.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doemais.api.models.ReputacaoUsuario;

public interface ReputacaoRepository extends JpaRepository<ReputacaoUsuario, Long>{
	ReputacaoUsuario findByIdAvaliacao(long id);

	@Query(value = "select avg(nota_avaliacao) from reputacao_usuario where id_usuario = ?", nativeQuery = true)
	double mediaReputacaoByIdUsuario(long id);
}
