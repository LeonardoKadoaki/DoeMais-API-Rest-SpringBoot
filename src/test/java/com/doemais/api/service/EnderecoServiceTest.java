package com.doemais.api.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.doemais.api.dto.EnderecoDto;
import com.doemais.api.models.Usuario;
import com.doemais.api.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EnderecoServiceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Test
	public void TestEnderecoService() throws Exception {
	Usuario user = new Usuario();	
	EnderecoDto endereco = new EnderecoDto();
	user.setIdUsuario(1);
	
	endereco.setBairro("Teste");
	endereco.setCep("00000000");
	endereco.setCidade("Teste");
	endereco.setComplemento("Teste");
	endereco.setIdEndereco(1);
	endereco.setLogradouro("Teste");
	endereco.setNumero(111);
	endereco.setUf("SP");
	
	enderecoRepository.findByUsuarioIdUsuario(user.getIdUsuario());

	}
	
	
	
	
	
	
	
}
