package com.doemais.api.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


public class CadastroDto {
	
	@NotBlank(message = "{nome.not.blank}")
	@Size(min = 1, max = 70)
	private String nome;
	
	@NotBlank(message = "{email.not.blank}")
	@Email(message = "{email.not.valid}")
	private String email;
	
	@NotBlank(message = "{senha.not.blank}")
	@Size(min = 1, max = 80)
	private String senha;
	
	@NotBlank
	@Size(min = 1, max = 30)
	private String userName;

	@NotBlank
	@Size(min = 11, max = 11)
	private String cpf;

	@NotBlank
	@Past(message = "A data de nascimento não pode ser maior ou igual a data atual")
	private LocalDate dataNascimento;

	@NotBlank
	private String numeroCelular;

	@NotBlank
	@Size(min = 1, max = 1, message = "Insira F (Feminino), M (Masculino) ou O (Outros)")
	private String genero;
	
	@Size(min = 0, max = 300, message = "O texto deve conter no máximo 300 caracteres")
	private String sobre;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getSobre() {
		return sobre;
	}

	public void setSobre(String sobre) {
		this.sobre = sobre;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
		CadastroDto other = (CadastroDto) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}
}
