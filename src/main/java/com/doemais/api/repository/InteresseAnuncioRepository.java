package com.doemais.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doemais.api.models.UsuarioInteressadoAnuncio;

public interface InteresseAnuncioRepository extends JpaRepository<UsuarioInteressadoAnuncio, Long>{
	
	@Query(value="select * from usuario_interessado_anuncio where idAnuncio = ? and idUsuario = ?", nativeQuery=true)
	Optional <UsuarioInteressadoAnuncio> findByAnuncioIdAnuncio(long idAnuncio, long idUsuario);
	
//	@Query(value="select * from USUARIO_INTERESSADO_ANUNCIO where id_anuncio = ?", nativeQuery=true)
	Optional<List<UsuarioInteressadoAnuncio>> findAllByAnuncioIdAnuncio(long idAnuncio);
	
	@Query(value="select count(*) from usuario_interessado_anuncio where idUsuario = ? and idAnuncio = ?", nativeQuery=true)
	int countByUsuarioIdUsuario(long idUsuario, long idAnuncio);
}
