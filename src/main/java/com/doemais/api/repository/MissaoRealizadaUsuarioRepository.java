package com.doemais.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doemais.api.models.MissaoRealizadaUsuario;
import org.springframework.data.jpa.repository.Query;

public interface MissaoRealizadaUsuarioRepository extends JpaRepository<MissaoRealizadaUsuario, Long>{

    @Query(value = "select count(*) from missao_realizada_usuario where idUsuario = ? and idMissao = ?;", nativeQuery = true)
    int verificaMissaoRealizadaUsuario(long idUsuario, int idMissao);
}
