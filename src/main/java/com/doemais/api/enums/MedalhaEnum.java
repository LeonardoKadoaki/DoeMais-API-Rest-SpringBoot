package com.doemais.api.enums;

public enum MedalhaEnum {
	PRIMEIRA_DOACAO(1, "Medalha Doador Iniciante", "Medalha Ganha por Realizar 1 doação"),
	CINCO_DOACOES(2, "Medalha Doador Júnior", "Medalha Ganha por Realizar 5 doações"),
	CEM_DOACOES(3, "Medalha Doador Sênior", "Medalha Ganha por Realizar 100 doações"),
	TRES_DOACOES_EM_UM_MES(4, "Medalha de Caridade", "Medalha Ganha por Realizar 3 doações em 1 mês"),
	DOADOR_QUATRO_ESTRELAS(5, "Medalha Doador Top", "Medalha Doador 4 Estrelas"),
	DOADOR_CINCO_ESTRELAS(6, "Medalha de Herói", "Medalha Doador 5 Estrelas");
	
	public int idMedalha;
	public String nomeMedalha;
	public String descricaoMedalha;
	
	MedalhaEnum(int idMedalha, String nomeMedalha, String descricaoMedalha) {
		this.idMedalha = idMedalha;
		this.nomeMedalha = nomeMedalha;
		this.descricaoMedalha = descricaoMedalha;
	}

	public int getIdMedalha() {
		return idMedalha;
	}

	public void setIdMedalha(int idMedalha) {
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
	
	
	
	
	
}
