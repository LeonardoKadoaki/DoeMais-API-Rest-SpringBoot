package com.doemais.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doemais.api.models.ResumoReputacaoUsuario;

public interface ResumoReputacaoRepository extends JpaRepository<ResumoReputacaoUsuario, Long>{
	ResumoReputacaoUsuario findById(long id);

	Optional<ResumoReputacaoUsuario> findByUsuarioIdUsuario(long idusuario);
}
