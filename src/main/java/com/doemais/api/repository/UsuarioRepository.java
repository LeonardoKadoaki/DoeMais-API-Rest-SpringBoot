package com.doemais.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doemais.api.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Optional<Usuario> findByIdUsuario(long id);

	Optional<Usuario> findByCpf(String cpf);
	
	Optional<Usuario> findByNumeroCelular(String numeroCelular);
	
}
