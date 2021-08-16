package com.doemais.api.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "email")
public class Email implements Serializable {
	
	private static final long serialVersionUID = 1L;
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idEmail;

	@ManyToOne
	@JoinColumn(name = "usuarioRemet", nullable = false)
	private Usuario usuarioRemet;

	@ManyToOne
	@JoinColumn(name = "usuarioDest", nullable = false)
	private Usuario usuarioDest;

	@Column (nullable = false)
	@NotNull
	private String assunto;
	
	@Column(nullable = false)
	@NotNull
	private String corpoEmail;
	
	@Column(nullable = false)
	private LocalDateTime dataEnvioEmail;


	public long getIdEmail() {
		return idEmail;
	}

	public void setIdEmail(long idEmail) {
		this.idEmail = idEmail;
	}

	public Usuario getUsuarioRemet() {
		return usuarioRemet;
	}

	public void setUsuarioRemet(Usuario usuarioRemet) {
		this.usuarioRemet = usuarioRemet;
	}

	public Usuario getUsuarioDest() {
		return usuarioDest;
	}

	public void setUsuarioDest(Usuario usuarioDest) {
		this.usuarioDest = usuarioDest;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getCorpoEmail() {
		return corpoEmail;
	}

	public void setCorpoEmail(String corpoEmail) {
		this.corpoEmail = corpoEmail;
	}

	public LocalDateTime getDataEnvioEmail() {
		return dataEnvioEmail;
	}

	public void setDataEnvioEmail(LocalDateTime dataEnvioEmail) {
		this.dataEnvioEmail = dataEnvioEmail;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idEmail ^ (idEmail >>> 32));
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
		Email other = (Email) obj;
		if (idEmail != other.idEmail)
			return false;
		return true;
	}
}
