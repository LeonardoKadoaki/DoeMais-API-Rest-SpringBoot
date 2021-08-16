package com.doemais.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doemais.api.models.Missao;

public interface MissaoRepository extends JpaRepository<Missao, Long>{
	
	Optional<Missao> findById(long idMissao);

}
