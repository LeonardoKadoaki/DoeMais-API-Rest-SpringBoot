package com.doemais.api.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.doemais.api.repository.AuthRepository;
import com.doemais.api.security.services.AutenticacaoPorTokenFilter;
import com.doemais.api.security.services.AutenticacaoService;
import com.doemais.api.security.services.TokenService;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AuthRepository usuarioRepository;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}

	//Configuracoes de autorizacao
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/api/**").permitAll()
//		.antMatchers(HttpMethod.POST, "/api/usuario").permitAll()
////		.antMatchers(HttpMethod.GET, "/api/anuncio/**").permitAll() //TODO APENAS PARA TESTE. REMOVER NA VERSÃO FINAL
//		.antMatchers(HttpMethod.POST, "/auth").permitAll()
////		.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
//		.antMatchers("/actuator/**").permitAll()
////		.antMatchers(HttpMethod.GET, "/api/usuario/lista").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and().cors()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacaoPorTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
	}
	
	
	//Configuracoes de recursos estaticos(js, css, imagens, etc.)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/swagger-ui/*", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
	}
}
