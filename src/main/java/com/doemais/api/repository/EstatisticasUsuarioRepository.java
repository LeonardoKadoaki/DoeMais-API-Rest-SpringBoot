package com.doemais.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doemais.api.models.EstatisticasUsuario;

public interface EstatisticasUsuarioRepository extends JpaRepository<EstatisticasUsuario, Long> {
	
	Optional<EstatisticasUsuario> findByUsuarioIdUsuario(long idUsuario);
	
	@Query(value = "select eu.* from estatisticas_usuario eu inner join usuario u on eu.idUsuario = u.idUsuario order by totalMoedas desc;", nativeQuery = true)
	List<EstatisticasUsuario> consultarRanking();
}
