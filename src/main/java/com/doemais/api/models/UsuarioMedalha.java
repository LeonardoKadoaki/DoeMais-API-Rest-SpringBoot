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
@Table(name = "UsuarioMedalha")
public class UsuarioMedalha  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuarioMedalha;
	
	@NotNull
    @Column(nullable = false)
	private Date dataRegistro;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "idMedalha", nullable = false)
	private Medalha medalha;

	
	public long getIdUsuarioMedalha() {
		return idUsuarioMedalha;
	}

	public void setIdUsuarioMedalha(long idUsuarioMedalha) {
		this.idUsuarioMedalha = idUsuarioMedalha;
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

	public Medalha getMedalha() {
		return medalha;
	}

	public void setMedalha(Medalha medalha) {
		this.medalha = medalha;
	}

	@Override
	public String toString() {
		return "UsuarioMedalha [dataRegistro=" + dataRegistro + ", usuario=" + usuario + ", medalha=" + medalha + "]";
	}

	
	
	
	
	
}
