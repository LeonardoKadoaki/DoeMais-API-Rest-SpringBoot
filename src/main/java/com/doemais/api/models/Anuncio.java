 package com.doemais.api.models;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.doemais.api.enums.StatusAnuncioEnum;

@Entity
@Table(name = "ANUNCIO", indexes = @Index(columnList = "titulo"))
public class Anuncio {

//	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAnuncio;

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
	private Date dataFim;

	@Column
	private double notaAvaliacao;

	@ManyToOne
	@JoinColumn(name = "idStatus", nullable = false)
	private StatusAnuncio status;

	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuarioAnunciante;
	
//    @OneToMany(mappedBy = "anuncio")
//    private List<Endereco> enderecos;
	
//	public Anuncio(long idAnuncio, @NotNull String titulo, String descricao, long idCategoria, Date dataCriacao) {
//		super();
//		this.idAnuncio = idAnuncio;
//		this.titulo = titulo;
//		this.descricao = descricao;
//		this.categoria.setIdCategoria(idCategoria);
//		this.dataCriacao = dataCriacao;
//		
//	}

	public long getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(long idAnuncio) {
		this.idAnuncio = idAnuncio;
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

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public double getNotaAvaliacao() {
		return notaAvaliacao;
	}

	public void setNotaAvaliacao(double notaAvaliacao) {
		if (getNotaAvaliacao() != 0)
			throw new RuntimeException("Anúncio já avaliado");

		if (getStatus().getIdStatus() != StatusAnuncioEnum.CONCLUIDO.getValor())
			throw new RuntimeException("Anúncio não concluído");

		this.notaAvaliacao = notaAvaliacao;
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

	public StatusAnuncio getStatus() {
		return status;
	}

	public void setStatus(StatusAnuncio status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Anuncio [idAnuncio=" + idAnuncio + ", titulo=" + titulo + ", descricao=" + descricao + ", categoria="
				+ categoria + ", dataCriacao=" + dataCriacao + ", dataExpiracao=" + dataExpiracao + ", dataFim="
				+ dataFim + ", status=" + status + ", usuarioAnunciante=" + usuarioAnunciante + "]";
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
