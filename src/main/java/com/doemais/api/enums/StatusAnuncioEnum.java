package com.doemais.api.enums;

/* Anuncios e Pedidos usam os mesmos status */

public enum StatusAnuncioEnum {
	EM_ANDAMENTO(1),
	CONCLUIDO(2),
	EXPIRADO(3),
	CANCELADO(4),
	CONGELADO(5);

	public int valor;
	StatusAnuncioEnum(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return valor;
	}
}
