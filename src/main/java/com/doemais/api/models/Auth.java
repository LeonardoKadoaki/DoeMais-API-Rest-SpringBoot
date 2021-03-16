package com.doemais.api.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "AUTH")
public class Auth implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAuth;
	
	@NotNull
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuario;
	
	@NotNull
	@Column(nullable = false, unique=true, length = 50)
	@Email(message = "Insira um email válido")
	private String email;
	
	@NotNull
	@Size(min = 1, max = 80)
	@Column(nullable = false, length = 80)
	private String senha;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();

	public Auth() {
		super();
	}
	
	public Auth(@NotNull Usuario usuario,
			@NotNull @Email(message = "Insira um email válido") String email,
			@NotNull @Size(min = 1, max = 80) String senha) {
		super();
		this.usuario = usuario;
		this.email = email;
		this.senha = senha;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAuth == null) ? 0 : idAuth.hashCode());
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
		Auth other = (Auth) obj;
		if (idAuth == null) {
			if (other.idAuth != null)
				return false;
		} else if (!idAuth.equals(other.idAuth))
			return false;
		return true;
	}

	public Long getIdAuth() {
		return idAuth;
	}

	public void setIdAuth(Long idAuth) {
		this.idAuth = idAuth;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
