package com.doemais.api.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


@Entity
@Table(name = "usuario_auditoria")
public class UsuarioAuditoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuarioAuditoria;
	
	@NotNull
	@Column(nullable = false, length = 70)
	@Size(min = 1, max = 70)
	private String nome;
	
	@NotNull
	@Column(nullable = false,  unique = true, length = 11)
	private String cpf;
	
	@NotNull
	@Column(nullable = false, unique = true, length = 11)
	private String rg;
	
	@Column(nullable = false)
	private Date dataCadastro;
	
	@NotNull
	@Column(nullable = false)
	@Past(message = "A Data de Nascimento não pode ser maior ou igual a data atual")
	private Date dataNascimento;
	
	
	@Column(nullable = false, length = 100)
	private String fotoPerfil;
	
	@NotNull
	@Column(nullable = false, length = 1)
	@Size(min = 1, max = 1, message = "Insira F (Feminino), M (Masculino) ou O (Outros)")
	private String genero;
	
	@NotNull
	@Column(unique = true, length = 14)
	private String numeroCelular;
	
	@Column(nullable = false, length = 300)
	@Size(min = 1, max = 300, message = "O texto deve conter no máximo 300 caracteres")
	private String sobre;
	
	@NotNull
	@Column(nullable = false, length = 30)
	@Size(min = 11, max = 30)
	private String userName;
	
	@Column(nullable = false)
	private Date dataAuditoria;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuario;

	
	
	
	
	
	public long getIdUsuarioAuditoria() {
		return idUsuarioAuditoria;
	}

	public void setIdUsuarioAuditoria(long idUsuarioAuditoria) {
		this.idUsuarioAuditoria = idUsuarioAuditoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public String getSobre() {
		return sobre;
	}

	public void setSobre(String sobre) {
		this.sobre = sobre;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDataAuditoria() {
		return dataAuditoria;
	}

	public void setDataAuditoria(Date dataAuditoria) {
		this.dataAuditoria = dataAuditoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public UsuarioAuditoria(long idUsuarioAuditoria, @NotNull @Size(min = 1, max = 70) String nome,
			@NotNull  String cpf, @NotNull String rg, Date dataCadastro,
			@NotNull @Past(message = "A Data de Nascimento não pode ser maior ou igual a data atual") Date dataNascimento,
			String fotoPerfil, @NotNull @Size(min = 1, max = 1) String genero, @NotNull String numeroCelular,
			@Size(min = 1, max = 300) String sobre, @NotNull @Size(min = 11, max = 30) String userName,
			Date dataAuditoria, Usuario usuario) {
		super();
		this.idUsuarioAuditoria = idUsuarioAuditoria;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.dataCadastro = dataCadastro;
		this.dataNascimento = dataNascimento;
		this.fotoPerfil = fotoPerfil;
		this.genero = genero;
		this.numeroCelular = numeroCelular;
		this.sobre = sobre;
		this.userName = userName;
		this.dataAuditoria = dataAuditoria;
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idUsuarioAuditoria ^ (idUsuarioAuditoria >>> 32));
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
		UsuarioAuditoria other = (UsuarioAuditoria) obj;
		if (idUsuarioAuditoria != other.idUsuarioAuditoria)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsuarioAuditoria [idUsuarioAuditoria=" + idUsuarioAuditoria + ", nome=" + nome + ", cpf=" + cpf
				+ ", rg=" + rg + ", dataCadastro=" + dataCadastro + ", dataNascimento=" + dataNascimento
				+ ", fotoPerfil=" + fotoPerfil + ", genero=" + genero + ", numeroCelular=" + numeroCelular + ", sobre="
				+ sobre + ", userName=" + userName + ", dataAuditoria=" + dataAuditoria + ", usuario=" + usuario + "]";
	}
	
	
	
	
}
