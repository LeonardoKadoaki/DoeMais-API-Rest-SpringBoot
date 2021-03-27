package com.doemais.api.enums;

public enum InteresseEnum {
	INTERESSADO(1), 
	EM_ANDAMENTO(2), 
	CONCLUIDO(3);
	
	public int valor;
	InteresseEnum(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return valor;
	}
}
