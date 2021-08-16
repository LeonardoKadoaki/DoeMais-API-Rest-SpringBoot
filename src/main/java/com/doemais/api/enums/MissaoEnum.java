package com.doemais.api.enums;

public enum MissaoEnum {
	PRIMEIRA_DOACAO(1, "Conclua sua primeira doação", 100),
	CINCO_DOACOES(2, "Conclua cinco doações", 350),
	CEM_DOACOES(3, "Conclua 100 doações", 10000),
	TRES_DOACOES_EM_UM_MES(4, "Conclua três doações em um período de um mês", 500),
	DOADOR_QUATRO_ESTRELAS(5, "Seja um doador 4 estrelas", 500),
	DOADOR_CINCO_ESTRELAS(6, "Seja um doador 5 estrelas", 750);
	
	public int id;
	public String descricao;
	public int moedas;
	
	MissaoEnum(int id, String descricao, int moedas) {
		this.id = id;
		this.descricao = descricao;
		this.moedas = moedas;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public int getMoedas() {
		return moedas;
	}
}
