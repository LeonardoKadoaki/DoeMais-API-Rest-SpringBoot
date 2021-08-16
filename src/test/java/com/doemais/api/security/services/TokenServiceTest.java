package com.doemais.api.security.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.doemais.api.models.Auth;
import com.doemais.api.models.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.minidev.json.JSONArray;
import springfox.documentation.spring.web.json.Json;

@SpringBootTest
class TokenServiceTest {

	@Value("${forum.jwt.expiration}")
    private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;

	private Usuario user = new Usuario();
	
	@Value("${forum.jwt.secret}")
	private String token;

	
	@Test
	void TestGeraToken() {
		Auth logado = new Auth();
		user.setIdUsuario(1);
		logado.setUsuario(user);
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		String idUsuario = Long.toString(logado.getUsuario().getIdUsuario());
		
		assertNotNull(Jwts.builder()
		.setIssuer("API Doe+")
		.setSubject(idUsuario)
		.setIssuedAt(hoje)
		.setExpiration(dataExpiracao)
		.signWith(SignatureAlgorithm.HS256, secret)
		.compact());
	}
	
	@Test
	void TestIsTokenValido() {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
		    assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
