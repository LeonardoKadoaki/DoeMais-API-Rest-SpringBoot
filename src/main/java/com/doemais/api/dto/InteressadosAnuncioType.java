package com.doemais.api.dto;

public class InteressadosAnuncioType {
	private long idUsuarioInteressado;
	
	private long idAnuncio;
	
	private String nome;

	public InteressadosAnuncioType(long idUsuarioInteressado, long idAnuncio, String nome) {
		this.idUsuarioInteressado = idUsuarioInteressado;
		this.idAnuncio = idAnuncio;
		this.nome = nome;
	}

	public InteressadosAnuncioType() {
		
	}
	
	public long getIdUsuarioInteressado() {
		return idUsuarioInteressado;
	}

	public void setIdUsuarioInteressado(long idUsuarioInteressado) {
		this.idUsuarioInteressado = idUsuarioInteressado;
	}

	public long getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(long idAnuncio) {
		this.idAnuncio = idAnuncio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
