package com.doemais.api.dto;

import com.doemais.api.enums.StatusAnuncioEnum;

public class StatusAnuncioDto {

	private long idAnuncio;
	private long idDonatario;
	private StatusAnuncioEnum status;

	public long getIdDonatario() {
		return idDonatario;
	}

	public void setIdDonatario(long idDonatario) {
		this.idDonatario = idDonatario;
	}

	public long getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(long idAnuncio) {
		this.idAnuncio = idAnuncio;
	}

	public StatusAnuncioEnum getStatus() {
		return status;
	}

	public void setStatus(StatusAnuncioEnum status) {
		this.status = status;
	}

}
