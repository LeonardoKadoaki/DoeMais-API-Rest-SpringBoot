package com.doemais.api.dto;

public class PosicaoRankingType {

	private long posicao;
	private String nome;
	private long totalMoedas;

	public PosicaoRankingType() {
	}

	public PosicaoRankingType(long posicao, String nome, long totalMoedas) {
		this.posicao = posicao;
		this.nome = nome;
		this.totalMoedas = totalMoedas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getTotalMoedas() {
		return totalMoedas;
	}

	public void setTotalMoedas(long totalMoedas) {
		this.totalMoedas = totalMoedas;
	}

	public long getPosicao() {
		return posicao;
	}

	public void setPosicao(long posicao) {
		this.posicao = posicao;
	}

}
