package com.doemais.api.repository;

import java.util.List;
import java.util.Optional;

import com.doemais.api.dto.DoadorDonatarioType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doemais.api.models.Anuncio;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

	Optional<Anuncio> findByIdAnuncio(long id);

	@Query(value = "select id_anuncio, data_criacao, data_expiracao, data_fim, descricao, titulo, id_status, id_categoria, id_usuario from anuncio where id_usuario = ?", nativeQuery = true)
	Optional<List<Anuncio>> findAllByIdUsuario(long id);

	@Query(value = "select * from anuncio where titulo like ?", nativeQuery = true)
	Optional<List<Anuncio>> findAllByTitulo(String titulo);

	@Query(value = "select a.* from anuncio a inner join usuario u on a.id_usuario = u.id_usuario inner join endereco e on u.id_usuario = e.id_usuario where e.cidade = ?;", nativeQuery = true)
	List<Anuncio> findAllByCidade(String cidade);

	@Query("SELECT v FROM Anuncio v INNER JOIN FETCH v.categoria c INNER JOIN FETCH v.status s "
			+ "INNER JOIN FETCH v.usuarioAnunciante u "
			+ "WHERE s.idStatus = :idStatus ")
	public List<Anuncio> buscarAnuncioComPaginacao(long idStatus, final Pageable pageable);

	@Query(value = "select * from anuncio where titulo like ? and id_status = ?", nativeQuery = true)
	public List<Anuncio> buscarAnuncioPorTituloComPaginacao(String titulo, long idStatus, final Pageable pageable);
	
	@Query(value = "select a.* from anuncio a inner join usuario u on a.id_usuario = u.id_usuario inner join endereco e on u.id_usuario = e.id_usuario where e.cidade = ? and id_status = ?", nativeQuery = true)
	public List<Anuncio> buscarAnuncioPorCidadeComPaginacao(String cidade, long idStatus, final Pageable pageable);
	
	@Query(value = "update anuncio set id_status = ? where id_anuncio = ?", nativeQuery = true)
	void atualizarStatusAnuncio(int idStatus, long idAnuncio);

//	@Query(value = "select * from anuncio where id_anuncio = ?", nativeQuery = true)
//	Anuncio getDoadorDonatarioByIdAnuncio(long idAnuncio);
}
