package com.doemais.api.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.doemais.api.models.Auth;

public interface AuthRepository extends JpaRepository<Auth, Long> {

	Auth findByEmail(String email);
	
	Auth findByUsuarioIdUsuario(long idUsuario);
	
	@Transactional
	@Modifying
	@Query(value = "update auth set senha=? where idUsuario=?;", nativeQuery = true)
	void updatePassword(String senha, Long idUsuario);
	
	@Transactional
	@Modifying
	@Query(value="delete from auth where idUsuario=?;", nativeQuery=true)
	void deleteByUsuarioIdUsuario(long idUsuario);
}
