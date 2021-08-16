package com.doemais.api.dto;

public class InteresseType {

	private long idUsuario;
	
	private long idAnuncio;

	public InteresseType(long idUsuario, long idAnuncio) {
		this.idUsuario = idUsuario;
		this.idAnuncio = idAnuncio;
	}
	
	public InteresseType() {
		
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public long getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(long idAnuncio) {
		this.idAnuncio = idAnuncio;
	}
}
