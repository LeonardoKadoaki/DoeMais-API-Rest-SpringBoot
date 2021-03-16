package com.doemais.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ENDERECO", indexes = @Index(columnList = "cidade"))
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idEndereco;

//	@ManyToOne
//	@JoinColumn(name = "idAnuncio", nullable = true)
//	private Anuncio anuncio;

	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuario;

	@NotNull
	@Column(nullable = false, length = 80)
	private String logradouro;

	@NotNull
	@Column(nullable = false)
	private int numero;

	@NotNull
	@Column(nullable = false, length = 8)
	private String cep;

	@Column(nullable = true, length = 100)
	private String complemento;

	@NotNull
	@Column(nullable = false, length = 50)
	private String bairro;

	@NotNull
	@Column(nullable = false, length = 30)
	private String cidade;

	@NotNull
	@Column(nullable = false, length = 2)
	private String uf;
	
	@NotNull
	@Column(nullable = false, length = 30)
	private String localidade;

//	Não acho que tem necessidade de país
//	private String pais;

	
	
	
	public String getLocalidade() {
		return localidade;
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
		Endereco other = (Endereco) obj;
		if (idEndereco != other.idEndereco)
			return false;
		return true;
	}

	public void setLocalidade(String localidade) {
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
