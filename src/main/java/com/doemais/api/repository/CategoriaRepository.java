package com.doemais.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doemais.api.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	Optional <Categoria> findById(long id);
	
	
}
