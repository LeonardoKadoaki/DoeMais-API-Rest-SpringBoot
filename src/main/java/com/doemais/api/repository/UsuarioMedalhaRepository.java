package com.doemais.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doemais.api.models.Anuncio;
import com.doemais.api.models.UsuarioMedalha;


public interface UsuarioMedalhaRepository extends JpaRepository<UsuarioMedalha, Long> {
	
	@Query(value = "select count(*) from usuario_medalha where idUsuario = ? and idMedalha = ?;", nativeQuery = true)
    int verificaUsuarioMedalha(long idUsuario, int idMedalha);
	
	@Query(value = "select * from usuario_medalha where idUsuario = ?", nativeQuery = true)
	Optional<List<UsuarioMedalha>> findAllByIdUsuario(long id);
	
}
