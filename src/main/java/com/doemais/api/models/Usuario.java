package com.doemais.api.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuario;

	@NotNull
	@Size(min = 1, max = 70)
	@Column(nullable = false, length = 70)
	private String nome;

	@NotNull
	@Size(min = 1, max = 30)
	@Column(nullable = false, length = 30)
	private String userName;

	@NotNull
	@Column(nullable = false, unique = true, length = 11)
	@Size(min = 11, max = 11)
	private String cpf;

	@Column(nullable = true, unique = true, length = 11)
	@Size(min = 0, max = 11)
	private String rg;

	@NotNull
	@Column(nullable = false)
	@Past(message = "A data de nascimento não pode ser maior ou igual a data atual")
	private LocalDate dataNascimento;

	@Column(nullable = false)
	private LocalDate dataCadastro;

	@NotNull
	@Column(unique = true, length = 14)
//	@Pattern(regexp = "\\(\\d{2}\\)\\d{5}-\\d{4}")
	private String numeroCelular;

	@NotNull
	@Column(nullable = false, length = 1)
	@Size(min = 1, max = 1, message = "Insira F (Feminino), M (Masculino) ou O (Outros)")
	private String genero;

	@Column(nullable = true, length = 300)
	@Size(min = 0, max = 300, message = "O texto deve conter no máximo 300 caracteres")
	private String sobre;

	@Column(nullable = true, length = 200)
	private String fotoPerfil;

	public Usuario() {
		super();
	}

	public Usuario(@NotNull @Size(min = 1, max = 70) String nome, @NotNull @Size(min = 1, max = 30) String userName,
			@NotNull @Size(min = 11, max = 11) String cpf,
			@NotNull @Past(message = "A data de nascimento não pode ser maior ou igual a data atual") LocalDate dataNascimento, 
			LocalDate dataCadastro,
			@NotNull @Pattern(regexp = "\\(\\d{2}\\)\\d{5}-\\d{4}") String numeroCelular,
			@NotNull @Size(min = 1, max = 1, message = "Insira F (Feminino), M (Masculino) ou O (Outros)") String genero,
			@Size(min = 0, max = 300, message = "O texto deve conter no máximo 300 caracteres") String sobre) {
		super();
		this.nome = nome;
		this.userName = userName;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.dataCadastro = dataCadastro;
		this.numeroCelular = numeroCelular;
		this.genero = genero;
		this.sobre = sobre;
	}
	
	/*public Usuario(@NotNull @Size(min = 1, max = 70) String nome, @NotNull @Size(min = 1, max = 30) String userName,
			@NotNull @Size(min = 11, max = 11) String cpf, @NotNull @Size(min = 11, max = 11) String rg,
			@NotNull @Past(message = "A data de nascimento não pode ser maior ou igual a data atual") Date dataNascimento,
			@NotNull @Pattern(regexp = "\\(\\d{2}\\)\\d{5}-\\d{4}") String numeroCelular,
			@NotNull @Size(min = 1, max = 1, message = "Insira F (Feminino), M (Masculino) ou O (Outros)") String genero,
			@Size(min = 0, max = 300, message = "O texto deve conter no máximo 300 caracteres") String sobre) {
		super();
		this.nome = nome;
		this.userName = userName;
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		this.numeroCelular = numeroCelular;
		this.genero = genero;
		this.sobre = sobre;
	}*/

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
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

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getSobre() {
		return sobre;
	}

	public void setSobre(String sobre) {
		this.sobre = sobre;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idUsuario ^ (idUsuario >>> 32));
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
		Usuario other = (Usuario) obj;
		if (idUsuario != other.idUsuario)
			return false;
		return true;
	}
}
