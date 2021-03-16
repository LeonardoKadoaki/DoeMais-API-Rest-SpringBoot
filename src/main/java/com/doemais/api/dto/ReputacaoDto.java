package com.doemais.api.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReputacaoDto {
	
	@NotNull
	private double notaAvaliacao;

	private LocalDate dataRegistro;

	@NotNull
	@Size(min = 1, max = 15)
	private String papelUsuario;


	public double getNotaAvaliacao() {
		return notaAvaliacao;
	}

	public void setNotaAvaliacao(double notaAvaliacao)
	throws IllegalArgumentException {
		if (notaAvaliacao < 1 || notaAvaliacao > 5)
			throw new IllegalArgumentException(
					String.format("A nota da avaliacao deve ser >= 1 e <= 5, mas é %.2f", notaAvaliacao));

		this.notaAvaliacao = notaAvaliacao;
	}

	public LocalDate getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDate dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public String getPapelUsuario() {
		return papelUsuario;
	}

	public void setPapelUsuario(String papelUsuario) {
		this.papelUsuario = papelUsuario;
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
