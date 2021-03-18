package com.doemais.api.dto;

import java.util.List;

import com.doemais.api.models.AnuncioFotos;

public class AnuncioFotosType {

	private long idAnuncio;
	private List<AnuncioFotos> fotos;

	public long getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(long idAnuncio) {
		this.idAnuncio = idAnuncio;
	}

	public List<AnuncioFotos> getFotos() {
		return fotos;
	}

	public void setFotos(List<AnuncioFotos> fotos) {
		this.fotos = fotos;
	}

}
