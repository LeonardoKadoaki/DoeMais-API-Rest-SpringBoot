package com.doemais.api.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.doemais.api.models.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	Optional<Endereco> findByUsuarioIdUsuario(long idUsuario);
	
	@Transactional
	@Modifying
	@Query(value="delete from endereco where idUsuario=?;", nativeQuery=true)
	void deleteByUsuarioIdUsuario(long idUsuario);
}
