package com.doemais.api.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.doemais.api.dto.CadastroDto;
import com.doemais.api.dto.ReputacaoDto;
import com.doemais.api.models.ReputacaoUsuario;
import com.doemais.api.models.ResumoReputacaoUsuario;
import com.doemais.api.models.Usuario;
import com.doemais.api.services.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private UsuarioService userService;
	
	private CadastroDto cadastroDto = new CadastroDto();
	private Usuario usuario = new Usuario();
	private ReputacaoDto dto = new ReputacaoDto();
	private ResumoReputacaoUsuario reputacaoUse = new ResumoReputacaoUsuario();
	private ReputacaoUsuario reputaUsu = new ReputacaoUsuario();
	

	

	@BeforeEach
	void configuracao() throws ParseException  {
		
		
		
		DateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
		Date data2 = formato2.parse("05/06/2021");
		cadastroDto = new CadastroDto();
		cadastroDto.setCpf("23545681601");
		cadastroDto.setDataNascimento(LocalDate.of(1998, Month.DECEMBER, 22));
		cadastroDto.setEmail("mikeias@gmail.com");
		cadastroDto.setGenero("M");
		cadastroDto.setNome("Junit testes");
		cadastroDto.setNumeroCelular("1154572457");
		cadastroDto.setSenha("testes Junit");
		cadastroDto.setSobre("testando o Junit");
		cadastroDto.setUserName("Junit testes");
		
		
		usuario = new Usuario("user teste", "mikeias", "23545681601", LocalDate.of(2020, Month.DECEMBER, 3),
				LocalDate.of(1998, Month.DECEMBER, 22), "mikeias Junit", "mike ", "mikeias testando");
		usuario.setIdUsuario(2);
		
		dto = new ReputacaoDto(1, "teste mikeias", 2, "teste mike");
		
	    reputacaoUse = new ResumoReputacaoUsuario(1, "Testessss", 2, data2, usuario);   
	        
	}
	
	
	@Test
	void listaUsuarioTest() throws Exception {
		Mockito.when(userService.buscarUsuarioPorId(usuario.getIdUsuario())).thenReturn(usuario);

		mockMvc.perform(get("/api/usuario/lista").contentType("application/json")).andExpect(status().isOk());

	}
	
	
	@Test
	void perfilPorIdTest() throws Exception {

		Mockito.when(userService.buscarUsuarioPorId(usuario.getIdUsuario())).thenReturn(usuario);

		mockMvc.perform(get("/api/usuario/perfil/" + usuario.getIdUsuario()).contentType("application/json"))
				.andExpect(status().isOk());

	}
	

	@Test
	void cadastraUsuarioTest() throws Exception {

		Mockito.when(userService.cadastrarUsuario(cadastroDto)).thenCallRealMethod();

		mockMvc.perform(
				post("/api/usuario").contentType("application/json").content(objectMapper.writeValueAsString(usuario)))
				.andExpect(status().isCreated());

	}
	
	@Test
	void removeUsuarioTest() throws JsonProcessingException, Exception {

		Mockito.doNothing().when(userService).deletarUsuario(usuario.getIdUsuario());

		mockMvc.perform(delete("/api/usuario/perfil/" + usuario.getIdUsuario()).contentType("application/json"))
				.andExpect(status().isOk());

	}


	@Test
	void atualizarUsuarioTest() throws JsonProcessingException, Exception {

		Mockito.when(userService.atualizarUsuario(usuario)).thenReturn(usuario);

		mockMvc.perform(put("/api/usuario/perfil/" + usuario.getIdUsuario()).contentType("application/json")
				.content(objectMapper.writeValueAsString(usuario))).andExpect(status().isOk());

	}

	@Test
	void retornaAvaliacaoUsuarioTest() throws Exception {

		Mockito.when(userService.buscarUsuarioPorId(usuario.getIdUsuario())).thenReturn(usuario);

		mockMvc.perform(
				get("/api/usuario/perfil/" + usuario.getIdUsuario() + "/avaliacao").contentType("application/json"))
				.andExpect(status().isOk());

	}

	@Test
	void avaliaUsuarioTest() throws Exception {

		Mockito.when(userService.registraAvaliacaoUsuario(dto, usuario.getIdUsuario())).thenReturn(reputaUsu);

		mockMvc.perform(post("/api/usuario/perfil/" + usuario.getIdUsuario() + "/avaliar").contentType("application/json")
				.content(objectMapper.writeValueAsString(dto))).andExpect(status().isOk());

	}

	
	
	
	

}
