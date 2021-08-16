package com.doemais.api.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "estatisticas_usuario")
public class EstatisticasUsuario implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idEstatisticas;
	
	private long totalMoedas;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuario;

	public long getIdEstatisticas() {
		return idEstatisticas;
	}

	public void setIdEstatisticas(long idEstatisticas) {
		this.idEstatisticas = idEstatisticas;
	}

	public long getTotalMoedas() {
		return totalMoedas;
	}

	public void setTotalMoedas(long totalMoedas) {
		this.totalMoedas = totalMoedas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idEstatisticas ^ (idEstatisticas >>> 32));
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
		EstatisticasUsuario other = (EstatisticasUsuario) obj;
		if (idEstatisticas != other.idEstatisticas)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EstatisticasUsuario [idEstatisticas=" + idEstatisticas + ", totalMoedas=" + totalMoedas + ", usuario="
				+ usuario + "]";
	}
}
