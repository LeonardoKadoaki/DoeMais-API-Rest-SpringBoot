package com.doemais.api.models;

import java.io.Serializable;
import java.time.LocalDateTime;
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

@Entity
@Table(name = "missao_realizada_usuario")
public class MissaoRealizadaUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idMissaoRealizada;
	
	@NotNull
	@Column(nullable = false)
	private LocalDateTime dataRegistro;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "idMissao", nullable = false)
	private Missao missao;

	public MissaoRealizadaUsuario(@NotNull LocalDateTime dataRegistro, Usuario usuario, Missao missao) {
		this.dataRegistro = dataRegistro;
		this.usuario = usuario;
		this.missao = missao;
	}
	
	public MissaoRealizadaUsuario() {
		
	}

	public long getIdMissaoRealizada() {
		return idMissaoRealizada;
	}

	public void setIdMissaoRealizada(long idMissaoRealizada) {
		this.idMissaoRealizada = idMissaoRealizada;
	}

	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Missao getMissao() {
		return missao;
	}

	public void setMissao(Missao missao) {
		this.missao = missao;
	}


	@Override
	public String toString() {
		return "MissaoRealizadaUsuario [dataRegistro=" + dataRegistro + ", usuario=" + usuario + ", missao=" + missao
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idMissaoRealizada ^ (idMissaoRealizada >>> 32));
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
		MissaoRealizadaUsuario other = (MissaoRealizadaUsuario) obj;
		if (idMissaoRealizada != other.idMissaoRealizada)
			return false;
		return true;
	}
	
	
	
	
	
}
