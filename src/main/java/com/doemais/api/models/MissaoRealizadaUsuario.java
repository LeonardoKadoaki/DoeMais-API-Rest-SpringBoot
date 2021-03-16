package com.doemais.api.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MissaoRealizadaUsuario")
public class MissaoRealizadaUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idMissaoRealizada;
	
	@NotNull
	@Column(nullable = false)
	private Date dataRegistro;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "idMissao", nullable = false)
	private Missao missao;


	public long getIdMissaoRealizada() {
		return idMissaoRealizada;
	}

	public void setIdMissaoRealizada(long idMissaoRealizada) {
		this.idMissaoRealizada = idMissaoRealizada;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
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
	
	
	
	
	
}
