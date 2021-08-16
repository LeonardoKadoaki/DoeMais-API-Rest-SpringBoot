package com.doemais.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReputacaoDto {
	
	@NotNull
	private double notaAvaliacao;

	@NotNull
	@Size(min = 1, max = 15)
	private String papelUsuario;

	@NotNull
	private long idAvaliador;

	@Size(min = 1, max = 15)
	private String descricaoResumo;

	public ReputacaoDto(@NotNull double notaAvaliacao, @NotNull @Size(min = 1, max = 15) String papelUsuario,
			@NotNull long idAvaliador, @Size(min = 1, max = 15) String descricaoResumo) {
		this.notaAvaliacao = notaAvaliacao;
		this.papelUsuario = papelUsuario;
		this.idAvaliador = idAvaliador;
		this.descricaoResumo = descricaoResumo;
	}
	
	public ReputacaoDto() {
		
	}

	public double getNotaAvaliacao() {
		return notaAvaliacao;
	}

	public void setNotaAvaliacao(double notaAvaliacao)
			throws IllegalArgumentException {

		if (notaAvaliacao < 1 || notaAvaliacao > 5) {
			throw new IllegalArgumentException(
					String.format("A nota da avaliacao deve ser >= 1 e <= 5, mas é %.2f", notaAvaliacao));
		}

		this.notaAvaliacao = notaAvaliacao;
	}

	public String getPapelUsuario() {
		return papelUsuario;
	}

	public void setPapelUsuario(String papelUsuario) {
		this.papelUsuario = papelUsuario;
	}

	public long getIdAvaliador() {
		return idAvaliador;
	}

	public void setIdAvaliador(long idAvaliador) {
		this.idAvaliador = idAvaliador;
	}

	public String getDescricaoResumo() {
		return descricaoResumo;
	}

	public void setDescricaoResumo(String descricaoResumo) {
		this.descricaoResumo = descricaoResumo;
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
		ReputacaoDto other = (ReputacaoDto) obj;
		if (Double.doubleToLongBits(notaAvaliacao) != Double.doubleToLongBits(other.notaAvaliacao))
			return false;
		return true;
	}
}
