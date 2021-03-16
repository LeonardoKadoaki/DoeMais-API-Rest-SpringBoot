package com.doemais.api.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Medalha")
public class Medalha implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idMedalha;
	
	@NotNull
	@Column(nullable = false, length = 30)
	@Size(min = 1, max = 30)
	private String nomeMedalha;
	
	@NotNull
	@Column(nullable = false, length = 60)
	@Size(min = 1, max = 60)
	private String descricaoMedalha;

	public long getIdMedalha() {
		return idMedalha;
	}

	public void setIdMedalha(long idMedalha) {
		this.idMedalha = idMedalha;
	}

	public String getNomeMedalha() {
		return nomeMedalha;
	}

	public void setNomeMedalha(String nomeMedalha) {
		this.nomeMedalha = nomeMedalha;
	}

	public String getDescricaoMedalha() {
		return descricaoMedalha;
	}

	public void setDescricaoMedalha(String descricaoMedalha) {
		this.descricaoMedalha = descricaoMedalha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idMedalha ^ (idMedalha >>> 32));
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
		Medalha other = (Medalha) obj;
		if (idMedalha != other.idMedalha)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Medalha [idMedalha=" + idMedalha + ", nomeMedalha=" + nomeMedalha + ", descricaoMedalha="
				+ descricaoMedalha + "]";
	}
	
	
	
	
	
	
}
