package com.doemais.api.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPedido;

	@NotNull
	@Column(nullable = false, length = 40)
	private String titulo;

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

	@ManyToOne
	@JoinColumn(name = "idStatus", nullable = false)
	private StatusAnuncio status;

	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario solicitante;

	@Column
	private long idDoador;

	public Pedido() {
	}

	public Pedido(long idPedido, @NotNull String titulo, String descricao, Categoria categoria,
				  LocalDateTime dataCriacao, LocalDateTime dataExpiracao, LocalDateTime dataFim,
				  StatusAnuncio status, Usuario solicitante, long idDoador) {

		this.idPedido = idPedido;
		this.titulo = titulo;
		this.descricao = descricao;
		this.categoria = categoria;
		this.dataCriacao = dataCriacao;
		this.dataExpiracao = dataExpiracao;
		this.dataFim = dataFim;
		this.status = status;
		this.solicitante = solicitante;
		this.idDoador = idDoador;
	}

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(LocalDateTime dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public StatusAnuncio getStatus() {
		return status;
	}

	public void setStatus(StatusAnuncio status) {
		this.status = status;
	}

	public Usuario getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Usuario solicitante) {
		this.solicitante = solicitante;
	}

	public long getIdDoador() {
		return idDoador;
	}

	public void setIdDoador(long idDoador) {
		this.idDoador = idDoador;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pedido pedido = (Pedido) o;
		return idPedido == pedido.idPedido &&
				idDoador == pedido.idDoador &&
				titulo.equals(pedido.titulo) &&
				Objects.equals(descricao, pedido.descricao) &&
				categoria.equals(pedido.categoria) &&
				dataCriacao.equals(pedido.dataCriacao) &&
				dataExpiracao.equals(pedido.dataExpiracao) &&
				Objects.equals(dataFim, pedido.dataFim) &&
				status.equals(pedido.status) &&
				solicitante.equals(pedido.solicitante);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPedido, titulo, descricao, categoria, dataCriacao, dataExpiracao, dataFim, status, solicitante, idDoador);
	}

	@Override
	public String toString() {
		return "Pedido{" +
				"idPedido=" + idPedido +
				", titulo='" + titulo + '\'' +
				", descricao='" + descricao + '\'' +
				", categoria=" + categoria +
				", dataCriacao=" + dataCriacao +
				", dataExpiracao=" + dataExpiracao +
				", dataFim=" + dataFim +
				", status=" + status +
				", solicitante=" + solicitante +
				", idDoador=" + idDoador +
				'}';
	}
}
