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
@Table(name = "usuario_medalha")
public class UsuarioMedalha  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuarioMedalha;
	
	@NotNull
    @Column(nullable = false)
	private LocalDateTime dataRegistro;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "idMedalha", nullable = false)
	private Medalha medalha;

	
	public UsuarioMedalha(@NotNull  LocalDateTime dataRegistro, Usuario usuario, Medalha medalha) {
		this.dataRegistro = dataRegistro;
		this.usuario = usuario;
		this.medalha = medalha;
	}
	
    public UsuarioMedalha() {
		
	}
	

	public long getIdUsuarioMedalha() {
		return idUsuarioMedalha;
	}

	public void setIdUsuarioMedalha(long idUsuarioMedalha) {
		this.idUsuarioMedalha = idUsuarioMedalha;
	}

	
	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Medalha getMedalha() {
		return medalha;
	}

	public void setMedalha(Medalha medalha) {
		this.medalha = medalha;
	}

	@Override
	public String toString() {
		return "UsuarioMedalha [dataRegistro=" + dataRegistro + ", usuario=" + usuario + ", medalha=" + medalha + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idUsuarioMedalha ^ (idUsuarioMedalha >>> 32));
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
		UsuarioMedalha other = (UsuarioMedalha) obj;
		if (idUsuarioMedalha != other.idUsuarioMedalha)
			return false;
		return true;
	}

	
	
	
	
	
}
