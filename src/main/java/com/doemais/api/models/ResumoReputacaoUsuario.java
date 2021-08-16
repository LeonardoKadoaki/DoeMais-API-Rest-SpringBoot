package com.doemais.api.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "resumo_reputacao_usuario")
public class ResumoReputacaoUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idResumo;
	
	@Column(length = 15)
	@Size(min = 1, max = 15)
	private String descricaoResumo;

	@NotNull
	@Column(nullable = false)
	private double notaAvaliacao;
	
	@NotNull
	@Column(nullable = false)
	private Date dataAtualizacaoRegistro;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;
	
	public ResumoReputacaoUsuario(long idResumo, @Size(min = 1, max = 15) String descricaoResumo,
			@NotNull double notaAvaliacao, @NotNull Date dataAtualizacaoRegistro, Usuario usuario) {
		this.idResumo = idResumo;
		this.descricaoResumo = descricaoResumo;
		this.notaAvaliacao = notaAvaliacao;
		this.dataAtualizacaoRegistro = dataAtualizacaoRegistro;
		this.usuario = usuario;
	}
	
	public ResumoReputacaoUsuario() {
		
	}

	public long getIdResumo() {
		return idResumo;
	}

	public void setIdResumo(long idResumo) {
		this.idResumo = idResumo;
	}

	public String getDescricaoResumo() {
		return descricaoResumo;
	}

	public void setDescricaoResumo(String descricaoResumo) {
		this.descricaoResumo = descricaoResumo;
	}

	public double getNotaAvaliacao() {
		return notaAvaliacao;
	}

	public void setNotaAvaliacao(double notaAvaliacao) {
		this.notaAvaliacao = notaAvaliacao;
	}

	public Date getDataAtualizacaoRegistro() {
		return dataAtualizacaoRegistro;
	}

	public void setDataAtualizacaoRegistro(Date dataAtualizacaoRegistro) {
		this.dataAtualizacaoRegistro = dataAtualizacaoRegistro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idResumo ^ (idResumo >>> 32));
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
		ResumoReputacaoUsuario other = (ResumoReputacaoUsuario) obj;
		if (idResumo != other.idResumo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResumoReputacaoUsuario [idResumo=" + idResumo + ", descricaoResumo=" + descricaoResumo
				+ ", notaAvaliacao=" + notaAvaliacao + ", dataAtualizacaoRegistro=" + dataAtualizacaoRegistro
				+ ", usuario=" + usuario + "]";
	}
}
