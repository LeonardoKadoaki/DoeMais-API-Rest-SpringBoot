package com.doemais.api.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.doemais.api.models.Auth;

public interface AuthRepository extends JpaRepository<Auth, Long> {

	Optional<Auth> findByEmail(String email);
	
//	Auth findByEmail(String email);
	
	Auth findByUsuarioIdUsuario(long idUsuario);
	
	@Transactional
	@Modifying
	@Query(value="delete from auth where id_usuario=?;", nativeQuery=true)
	void deleteByUsuarioIdUsuario(long idUsuario);
}
