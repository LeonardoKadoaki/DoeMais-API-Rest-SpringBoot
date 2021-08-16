package com.doemais.api.dto;

import javax.validation.constraints.NotNull;

public class EnderecoType {

	@NotNull
	private String logradouro;

	@NotNull
	private String cep;

	@NotNull
	private String complemento;

	@NotNull
	private String bairro;

	@NotNull
	private String uf;
	
	@NotNull
	private String localidade;
	
	public EnderecoType(@NotNull String logradouro, @NotNull String cep, @NotNull String complemento,
			@NotNull String bairro, @NotNull String uf, @NotNull String localidade) {
		this.logradouro = logradouro;
		this.cep = cep;
		this.complemento = complemento;
		this.bairro = bairro;
		this.uf = uf;
		this.localidade = localidade;
	}
	
	public EnderecoType() {
		
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	
}
