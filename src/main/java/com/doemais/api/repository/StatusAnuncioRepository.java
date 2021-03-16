package com.doemais.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doemais.api.models.StatusAnuncio;

public interface StatusAnuncioRepository extends JpaRepository<StatusAnuncio, Long>{
	
	 Optional<StatusAnuncio> findById(long id);
}