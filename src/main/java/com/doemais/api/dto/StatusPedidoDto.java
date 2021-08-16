package com.doemais.api.dto;

import com.doemais.api.enums.StatusAnuncioEnum;

public class StatusPedidoDto {

	private long idPedido;
	private long idDoador;
	private StatusAnuncioEnum status;

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public long getIdDoador() {
		return idDoador;
	}

	public void setIdDoador(long idDoador) {
		this.idDoador = idDoador;
	}

	public StatusAnuncioEnum getStatus() {
		return status;
	}

	public void setStatus(StatusAnuncioEnum status) {
		this.status = status;
	}
}
