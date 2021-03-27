package com.doemais.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doemais.api.models.InteresseAnuncio;

public interface InteresseAnuncioRepository extends JpaRepository<InteresseAnuncio, Long>{
	
	@Query(value="select * from USUARIO_INTERESSADO_ANUNCIO where id_anuncio = ? and id_usuario = ?", nativeQuery=true)
	Optional <InteresseAnuncio> findByAnuncioIdAnuncio(long idAnuncio, long idUsuario);
	
//	@Query(value="select * from USUARIO_INTERESSADO_ANUNCIO where id_anuncio = ?", nativeQuery=true)
	Optional<List<InteresseAnuncio>> findAllByAnuncioIdAnuncio(long idAnuncio);
	
	@Query(value="select count(*) from USUARIO_INTERESSADO_ANUNCIO where id_usuario = ? and id_anuncio = ?", nativeQuery=true)
	int countByUsuarioIdUsuario(long idUsuario, long idAnuncio);
}
