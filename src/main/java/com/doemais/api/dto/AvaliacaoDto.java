package com.doemais.api.dto;

import javax.validation.constraints.NotNull;

public class AvaliacaoDto {
	
	@NotNull
	private long idAnuncio;
	
	@NotNull
	private double notaAvaliacao;

	@NotNull
	private long idAvaliador;


	public double getNotaAvaliacao() {
		return notaAvaliacao;
	}

	public void setNotaAvaliacao(double notaAvaliacao)
			throws IllegalArgumentException {

		if (notaAvaliacao < 1 || notaAvaliacao > 5) {
			throw new IllegalArgumentException(
					String.format("notaAvaliacao deve ser >= 1 e <= 5, mas é %.2f", notaAvaliacao));
		}

		this.notaAvaliacao = notaAvaliacao;
	}

	public long getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(long idAnuncio) {
		this.idAnuncio = idAnuncio;
	}

	public long getIdAvaliador() {
		return idAvaliador;
	}

	public void setIdAvaliador(long idAvaliador) {
		this.idAvaliador = idAvaliador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(notaAvaliacao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		AvaliacaoDto other = (AvaliacaoDto) obj;
		if (Double.doubleToLongBits(notaAvaliacao) != Double.doubleToLongBits(other.notaAvaliacao))
			return false;
		return true;
	}
}
