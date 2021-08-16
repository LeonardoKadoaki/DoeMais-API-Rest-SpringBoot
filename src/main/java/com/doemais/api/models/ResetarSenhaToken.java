package com.doemais.api.models;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "redefine_senha")
public class ResetarSenhaToken {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String token;

	@OneToOne(targetEntity = Auth.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "idAuth")
	private Auth auth;

	@Column(nullable = false)
	private Date expiryDate;
	

	public ResetarSenhaToken(Long id, String token, Auth auth, Date expiryDate) {
		this.id = id;
		this.token = token;
		this.auth = auth;
		this.expiryDate = expiryDate;
	}
	
	public ResetarSenhaToken() {
		
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Auth getAuth() {
		return auth;
	}

	public void setAuth(Auth auth2) {
		this.auth = auth2;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public void setExpiryDate(int minutes) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, minutes);
		this.expiryDate = now.getTime();
	}

	public boolean isExpired() {
		return new Date().after(this.expiryDate);
	}
}
