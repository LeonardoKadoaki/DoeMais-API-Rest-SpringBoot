package com.doemais.api.dto;

import javax.validation.constraints.NotNull;

public class EnderecoDto {

	@NotNull
	private long idEndereco;

	@NotNull
	private String logradouro;

	@NotNull
	private int numero;

	@NotNull
	private String cep;

	@NotNull
	private String complemento;

	@NotNull
	private String bairro;

	@NotNull
	private String cidade;

	@NotNull
	private String uf;
	
	@NotNull
	private String localidade;
	
	public EnderecoDto() {
		super();
	}

	public EnderecoDto(@NotNull long idEndereco, @NotNull String logradouro, @NotNull int numero, @NotNull String cep,
			@NotNull String complemento, @NotNull String localidade, @NotNull String bairro, @NotNull String cidade, @NotNull String uf) {
		super();
		this.idEndereco = idEndereco;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.localidade = localidade;
	}



	public long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idEndereco ^ (idEndereco >>> 32));
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
		EnderecoDto other = (EnderecoDto) obj;
		if (idEndereco != other.idEndereco)
			return false;
		return true;
	}
	
	
	
	
	

}
