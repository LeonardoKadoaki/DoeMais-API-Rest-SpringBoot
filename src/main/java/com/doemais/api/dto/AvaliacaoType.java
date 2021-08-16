package com.doemais.api.dto;

public class AvaliacaoType {

	private double notaAvaliacao;
	private int numeroDeAvaliacoes;
	private String categoria;

	public double getAvaliacao() {
		return notaAvaliacao;
	}

	public void setAvaliacao(double avaliacao) {
		this.notaAvaliacao = avaliacao;
	}

	public int getNumeroDeAvaliacoes() {
		return numeroDeAvaliacoes;
	}

	public void setNumeroDeAvaliacoes(int numeroDeAvaliacoes) {
		this.numeroDeAvaliacoes = numeroDeAvaliacoes;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
