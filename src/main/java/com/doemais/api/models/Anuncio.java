package com.doemais.api.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.doemais.api.exception.ConflictException;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "anuncio", indexes = @Index(columnList = "titulo"))
public class Anuncio {

	/*
	 * NOTA Sempre que esta classe for modificada, deve-se modificar também o método
	 * Utilidades::forkAnuncio
	 */

//	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAnuncio;

	/**
	 * Em anúncios com vários itens, quando um "item" é fechado, este item fechado
	 * precisa apontar para o id do próximo item (recém-aberto)
	 */
	@NotNull
	@Column(nullable = false)
	private long nextIdAnuncio;

	@Field
	@NotNull
	@Column(nullable = false, length = 40)
	private String titulo;

	@Field
	@Column(length = 300)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "idCategoria", nullable = false)
	private Categoria categoria;

	@Column(nullable = false)
	private LocalDateTime dataCriacao;

	@Column(nullable = false)
	private LocalDateTime dataExpiracao;

	@Column
	private LocalDateTime dataFim;

	@Column
	private double notaAvaliacao;

	@Column
	private long idAvaliador;

	@Column
	private int qtdeItens = 1;

	@ManyToOne
	@JoinColumn(name = "idStatus", nullable = false)
	private StatusAnuncio status;

	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuarioAnunciante;

	@Column
	private long idDonatario;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idAnuncio", nullable = false)
	private List<AnuncioFotos> fotos;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Transient
	private String categoriaUsuarioAnunciante;

	public List<AnuncioFotos> getFotos() {
		return fotos;
	}

	public void setFotos(List<AnuncioFotos> fotos) {
		this.fotos = fotos;
	}

	public long getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(long idAnuncio) {
		this.idAnuncio = idAnuncio;
	}

	public long getNextIdAnuncio() {
		return nextIdAnuncio;
	}

	public void setNextIdAnuncio(long nextIdAnuncio) {
		this.nextIdAnuncio = nextIdAnuncio;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public double getNotaAvaliacao() {
		return notaAvaliacao;
	}

	public void setNotaAvaliacao(double notaAvaliacao) throws ConflictException {
		this.notaAvaliacao = notaAvaliacao;
	}

	public long getIdAvaliador() {
		return idAvaliador;
	}

	public void setIdAvaliador(long idAvaliador) {
		this.idAvaliador = idAvaliador;
	}

	public int getQtdeItens() {
		return qtdeItens;
	}

	public void setQtdeItens(int qtdeItens) {
		this.qtdeItens = qtdeItens;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public LocalDateTime getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(LocalDateTime dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public Usuario getUsuarioAnunciante() {
		return usuarioAnunciante;
	}

	public void setUsuarioAnunciante(Usuario usuarioAnunciante) {
		this.usuarioAnunciante = usuarioAnunciante;
	}

	public long getIdDonatario() {
		return idDonatario;
	}

	public void setIdDonatario(long idDonatario) {
		this.idDonatario = idDonatario;
	}

	public StatusAnuncio getStatus() {
		return status;
	}

	public void setStatus(StatusAnuncio status) {
		this.status = status;
	}

	public String getCategoriaUsuarioAnunciante() {
		return categoriaUsuarioAnunciante;
	}

	public void setCategoriaUsuarioAnunciante(String categoriaUsuarioAnunciante) {
		this.categoriaUsuarioAnunciante = categoriaUsuarioAnunciante;
	}

	public Anuncio(long idAnuncio, @NotNull long nextIdAnuncio, @NotNull String titulo, String descricao,
			double notaAvaliacao) {
		this.idAnuncio = idAnuncio;
		this.nextIdAnuncio = nextIdAnuncio;
		this.titulo = titulo;
		this.descricao = descricao;
		this.notaAvaliacao = notaAvaliacao;

	}

	public Anuncio() {

	}

	@Override
	public String toString() {
		return "Anuncio [idAnuncio=" + idAnuncio + ", titulo=" + titulo + ", descricao=" + descricao + ", categoria="
				+ categoria + ", dataCriacao=" + dataCriacao + ", dataExpiracao=" + dataExpiracao + ", dataFim="
				+ dataFim + ", status=" + status + ", usuarioAnunciante=" + usuarioAnunciante + ", donatario="
				+ idDonatario + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idAnuncio ^ (idAnuncio >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Anuncio other = (Anuncio) obj;
		if (idAnuncio != other.idAnuncio)
			return false;
		return true;
	}
}
