package com.doemais.api.models;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AnuncioFotos")
public class AnuncioFotos implements Serializable{
 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAnuncioFotos;
	
	@Column(nullable = true, length = 100)
	private String foto;
	
	@ManyToOne
	@JoinColumn(name = "idAnuncio", nullable = false)
	private  Anuncio anuncio;


	public long getIdAnuncioFotos() {
		return idAnuncioFotos;
	}

	public void setIdAnuncioFotos(long idAnuncioFotos) {
		this.idAnuncioFotos = idAnuncioFotos;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	@Override
	public String toString() {
		return "AnuncioFotos [foto=" + foto + ", anuncio=" + anuncio + "]";
	}
	

}
