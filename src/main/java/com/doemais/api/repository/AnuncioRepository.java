package com.doemais.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doemais.api.models.Anuncio;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

	Optional<Anuncio> findByIdAnuncio(long id);

	@Query(value = "select * from anuncio where idUsuario = ?", nativeQuery = true)
	Optional<List<Anuncio>> findAllByIdUsuario(long id);

	@Query(value = "select * from anuncio where titulo like ?", nativeQuery = true)
	Optional<List<Anuncio>> findAllByTitulo(String titulo);

	@Query(value = "select a.* from anuncio a inner join usuario u on a.idUsuario = u.idUsuario inner join endereco e on u.idUsuario = e.idUsuario where e.localidade = ?;", nativeQuery = true)
	List<Anuncio> findAllByCidade(String localidade);

	@Query("SELECT v FROM Anuncio v INNER JOIN FETCH v.categoria c INNER JOIN FETCH v.status s "
			+ "INNER JOIN FETCH v.usuarioAnunciante u "
			+ "WHERE s.idStatus = :idStatus ")
	public List<Anuncio> buscarAnuncioComPaginacao(long idStatus, final Pageable pageable);

//	@Query(value = "select * from anuncio where titulo like ? and idStatus = ?", nativeQuery = true)
//	public List<Anuncio> buscarAnuncioPorTituloComPaginacao(String titulo, long idStatus, final Pageable pageable);
	
	@Query(value = "select a.* from anuncio a inner join usuario u on a.idUsuario = u.idUsuario inner join endereco e on u.idUsuario = e.idUsuario where e.localidade = ? and idStatus = ?", nativeQuery = true)
	public List<Anuncio> buscarAnuncioPorCidadeComPaginacao(String localidade, long idStatus, final Pageable pageable);
	
	@Query(value = "update anuncio set idStatus = ? where idAnuncio = ?", nativeQuery = true)
	void atualizarStatusAnuncio(int idStatus, long idAnuncio);

//	@Query(value = "select * from anuncio where id_anuncio = ?", nativeQuery = true)
//	Anuncio getDoadorDonatarioByIdAnuncio(long idAnuncio);

	@Query(value = "select count(*) from anuncio where idUsuario = ? and idStatus = ?;", nativeQuery = true)
	int quantidadeDeAnunciosDoUsuario(long idUsuario, int status);
	
	@Query(value = "select count(*) from anuncio where idUsuario = ? and notaAvaliacao >= 4 and notaAvaliacao < 5;", nativeQuery = true)
	int quantidadeDeAnunciosQuatroEstrelasMais(long idUsuario);
	
	@Query(value = "select count(*) from anuncio where idUsuario = ? and notaAvaliacao = 5;", nativeQuery = true)
	int quantidadeDeAnunciosCincoEstrelas(long idUsuario);
}
