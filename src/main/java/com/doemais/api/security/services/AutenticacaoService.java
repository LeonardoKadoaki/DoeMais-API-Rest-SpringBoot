package com.doemais.api.security.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.doemais.api.models.Auth;
import com.doemais.api.repository.AuthRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(AutenticacaoService.class);
	
	@Autowired
	private AuthRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Auth> usuario = Optional.of(repository.findByEmail(username));
		
		if (usuario.isPresent()) {
			return usuario.get();
		}

		throw new UsernameNotFoundException("Dados inválidos!");
	}

}
