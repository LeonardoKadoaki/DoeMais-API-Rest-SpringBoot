package com.doemais.api.enums;

public enum StatusAnuncioEnum {
	EM_ANDAMENTO(1),
	CONCLUIDO(2),
	EXPIRADO(3),
	CANCELADO(4);

	public int valor;
	StatusAnuncioEnum(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return valor;
	}
}
