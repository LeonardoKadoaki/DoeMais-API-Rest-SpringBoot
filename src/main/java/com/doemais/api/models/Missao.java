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
@Table(name = "missao")
public class Missao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idMissao;
	
	@NotNull
	@Column(nullable = false, length = 90)
	@Size(min = 1, max = 90)
	private String descricaoMissao;
	
	@NotNull
	@Column(nullable = false)
	private int moedasMissao;

	public Missao(long idMissao, @NotNull @Size(min = 1, max = 90) String descricaoMissao, @NotNull int moedasMissao) {
		this.idMissao = idMissao;
		this.descricaoMissao = descricaoMissao;
		this.moedasMissao = moedasMissao;
	}
	
	public Missao() {
		
	}

	public long getIdMissao() {
		return idMissao;
	}

	public void setIdMissao(long idMissao) {
		this.idMissao = idMissao;
	}

	public String getDescricaoMissao() {
		return descricaoMissao;
	}

	public void setDescricaoMissao(String descricaoMissao) {
		this.descricaoMissao = descricaoMissao;
	}

	public int getMoedasMissao() {
		return moedasMissao;
	}

	public void setMoedasMissao(int moedasMissao) {
		this.moedasMissao = moedasMissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idMissao ^ (idMissao >>> 32));
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
		Missao other = (Missao) obj;
		if (idMissao != other.idMissao)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Missao [idMissao=" + idMissao + ", descricaoMissao=" + descricaoMissao + ", moedasMissao="
				+ moedasMissao + "]";
	}
	

}

