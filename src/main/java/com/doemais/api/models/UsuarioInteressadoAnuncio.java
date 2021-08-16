package com.doemais.api.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.doemais.api.enums.InteresseEnum;

@Entity
@Table(name = "usuario_interessado_anuncio")
public class UsuarioInteressadoAnuncio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idInteresseAnuncio;

	@ManyToOne
	@JoinColumn(name = "idAnuncio", nullable = false)
	private Anuncio anuncio;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuario;
	
	@Column(length = 20, nullable = false)
	private InteresseEnum status;
	
	@Column(nullable = false)
	private LocalDateTime dataRegistro;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public long getIdInteresseAnuncio() {
		return idInteresseAnuncio;
	}

	public void setIdInteresseAnuncio(long idInteresseAnuncio) {
		this.idInteresseAnuncio = idInteresseAnuncio;
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	public InteresseEnum getStatus() {
		return status;
	}

	public void setStatus(InteresseEnum status) {
		this.status = status;
	}

	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	
	
}
