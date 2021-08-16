package com.doemais.api.enums;

public enum CategoriaDoadorEnum {
	NONE("Sem categoria", -1),
	BRONZE("Bronze", 1),
	PRATA("Prata", 2),
	OURO("Ouro", 3),
	PLATINA("Platina", 4),
	DIAMANTE("Diamante", 4.5);

	public String nome;
	public double min;

	CategoriaDoadorEnum(String nome, double min) {
		this.nome = nome;
		this.min = min;
	}

	public String getNome() {
		return nome;
	}

	public double getMin() {
		return min;
	}
}
