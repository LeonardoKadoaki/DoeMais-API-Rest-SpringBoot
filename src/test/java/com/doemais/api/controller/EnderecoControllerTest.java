package com.doemais.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.doemais.api.dto.EnderecoType;
import com.doemais.api.models.Endereco;
import com.doemais.api.models.Usuario;
import com.doemais.api.repository.EnderecoRepository;
import com.doemais.api.services.EnderecoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class EnderecoControllerTest {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EnderecoService enderecoService;
	
	@MockBean
	private EnderecoRepository enderecoRepository;
	
	private Usuario user;
	private Usuario usuario;
	private Endereco endereco;
	private EnderecoType end;
	private List<Endereco> enderecolista = new ArrayList<>();
	
	@BeforeEach
	void Configuracao() {
	  user = new Usuario("testando", "testando@aluno.ifsp.edu.br", "teste1", LocalDate.of(2020, Month.DECEMBER, 3),
				LocalDate.of(1998, Month.DECEMBER, 22), "teste2", "teste2", "teste3");
	  user.setIdUsuario(6);
	  usuario = new Usuario("testando", "testando@aluno.ifsp.edu.br", "teste1", LocalDate.of(2020, Month.DECEMBER, 3),
				LocalDate.of(1998, Month.DECEMBER, 22), "teste2", "teste2", "teste3");
	  user.setIdUsuario(7);
	  
	  enderecolista.add(new Endereco());
	  
	   end = new EnderecoType("teste", "09942040", "teste", "teste", "teste", "Teste");
	  
	  endereco = new Endereco(1, "teste", 50, "09942040", "teste", "teste", "teste", "teste");
      endereco.setUsuario(user);
	  
      
      
	}
	
	@Test
	 void CadastraEnderecoTest() throws Exception{
		
		Mockito.when(enderecoService.cadastrarEndereco(endereco)).thenReturn(endereco);
		
		mockMvc.perform(post("/api/endereco")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(endereco)))
		        .andExpect(status().isCreated());

	}
	
	
	@Test
	 void ListaEnderecoPorIdTest() throws Exception {
		
		Mockito.when(enderecoService.buscarEnderecoPorIdUsuario(usuario.getIdUsuario())).thenReturn(endereco);
		
		mockMvc.perform(get("/api/endereco/usuario/" + usuario.getIdUsuario())
				.contentType("application/json"))
		         .andExpect(status().isOk());
		
	}
	
	
	@Test
	 void RetornaEnderecoPeloCepTest() throws Exception {
		
		Mockito.when(enderecoService.pesquisarEndereco(endereco.getCep())).thenReturn(end);
		
		mockMvc.perform(get("/api/endereco/buscarEndereco/" + endereco.getCep())
				.contentType("application/json"))
		        .andExpect(status().isOk());
		
	}
	
	
	@Test
	 void DeletaEnderecoUsuarioTest() throws Exception{
		
		Mockito.doNothing().when(enderecoService).deletarEndereco(user);
		
		mockMvc.perform(delete("/api/endereco/usuario/" + usuario.getIdUsuario())
				.contentType("application/json"))
		        .andExpect(status().isOk());
		
		
		
	}
	
	
	@Test
	 void AtualizaEnderecoUsuarioTest() throws Exception {
		Mockito.when(enderecoRepository.save(endereco)).thenReturn(endereco);
		
		mockMvc.perform(put("/api/endereco/usuario/" + usuario.getIdUsuario())
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(endereco)))
		        .andExpect(status().isOk());

		
	}
	
	
}
