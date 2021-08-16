package com.doemais.api.models;

import java.io.Serializable;
import java.time.LocalDate;

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
@Table(name = "reputacao_usuario")
public class ReputacaoUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAvaliacao;
	
	@NotNull
	@Column(nullable = false)
	private double notaAvaliacao;
	
	@NotNull
	@Column(nullable = false)
	private LocalDate dataRegistro;
	
	@NotNull
	@Column(nullable = false, length = 15)
	@Size(min = 1, max = 15)
	private String papelUsuario;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuario;

	@NotNull
	@Column(nullable = false)
	private long idAvaliador;

	public long getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(long idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}

	public double getNotaAvaliacao() {
		return notaAvaliacao;
	}

	public void setNotaAvaliacao(double notaAvaliacao) {
		this.notaAvaliacao = notaAvaliacao;
	}

	public LocalDate getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDate dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public String getPapelUsuario() {
		return papelUsuario;
	}

	public void setPapelUsuario(String papelUsuario) {
		this.papelUsuario = papelUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public long getIdAvaliador() {
		return idAvaliador;
	}

	public void setIdAvaliador(long idAvaliador) {
		this.idAvaliador = idAvaliador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idAvaliacao ^ (idAvaliacao >>> 32));
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
		ReputacaoUsuario other = (ReputacaoUsuario) obj;
		if (idAvaliacao != other.idAvaliacao)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReputacaoUsuario [idAvaliacao=" + idAvaliacao + ", notaAvaliacao=" + notaAvaliacao + ", dataRegistro="
				+ dataRegistro + ", papelUsuario=" + papelUsuario + ", usuario=" + usuario + "]";
	}
}
