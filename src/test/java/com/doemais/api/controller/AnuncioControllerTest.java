package com.doemais.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.doemais.api.dto.CadastroDto;
import com.doemais.api.dto.AvaliacaoDto;

import com.doemais.api.models.Anuncio;
import com.doemais.api.models.Categoria;
import com.doemais.api.models.StatusAnuncio;
import com.doemais.api.models.Usuario;
import com.doemais.api.repository.AnuncioRepository;
import com.doemais.api.repository.CategoriaRepository;
import com.doemais.api.repository.StatusAnuncioRepository;
import com.doemais.api.services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AnuncioControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private AnuncioRepository anuncioRepository;
	
	@Autowired
	private StatusAnuncioRepository statusAnuncioRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	
	@Test
	public void TestUsuarioListaAnuncioPorId() throws Exception {
		   
		Usuario user = new Usuario();
		
		mockMvc.perform(get("/api/anuncio/usuario/"  + user.getIdUsuario())
			    .contentType("application/json"))
		        .andExpect(status().isOk());
	
		
	}
	
	
	@Test
	public void TestListaAnuncioPorId() throws Exception {
		
		Anuncio anuncio = new Anuncio();

		mockMvc.perform(get("/api/anuncio/" + anuncio.getIdAnuncio())
				.contentType("application/json"))
		        .andExpect(status().isOk());
	}
	
	
	@Test
	public void TestCadastrarAnuncio() throws Exception {
		Anuncio anuncio = new Anuncio();
		CadastroDto cadastroDto = new CadastroDto();
		Categoria categoria = new Categoria();
		StatusAnuncio statusAnuncio = new StatusAnuncio();
		Usuario usuario = new Usuario();
		
		categoria.setIdCategoria(1);
		categoria.setNome("Teste");
		categoria.setMoedasCategoria(1);
		categoriaRepository.save(categoria);

		statusAnuncio.setIdStatus(1);
		statusAnuncio.setDescricaoStatus("teste");
		statusAnuncioRepository.save(statusAnuncio);
	
		
		cadastroDto.setNome("Tegfgdhio");
		cadastroDto.setEmail("ggfhgu@gl.com");
		cadastroDto.setSenha("4454");
		cadastroDto.setUserName("tehgo");
		cadastroDto.setCpf("4454");
		cadastroDto.setDataNascimento(LocalDate.of(1998, Month.DECEMBER, 22));
		cadastroDto.setDataCadastro(LocalDate.of(2021, Month.MARCH, 6));
		cadastroDto.setNumeroCelular("352");
		cadastroDto.setGenero("F");
		cadastroDto.setSobre("Tesh");
		
		usuario = usuarioService.cadastrarUsuario(cadastroDto);
	

		anuncio.setTitulo("SENHOR DOS ANEIS");
		anuncio.setDescricao("LIVRO");
		anuncio.setStatus(statusAnuncio);
		anuncio.setNotaAvaliacao(2);
		anuncio.setCategoria(categoria);
		anuncio.setUsuarioAnunciante(usuario);
		DateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
		Date data3 = formato2.parse("30/05/2021");
		anuncio.setDataCriacao(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		anuncio.setDataExpiracao(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		anuncio.setDataFim(data3);
		
		
		
		mockMvc.perform(post("/api/anuncio/cadastrar")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(anuncio)))
		        .andExpect(status().isOk());
		
		
	}
	
	@Test
	public void TestDeletarAnuncio() throws Exception {
		
		Anuncio anuncio = new Anuncio();
		Categoria categoria = new Categoria();
		StatusAnuncio statusAnuncio = new StatusAnuncio();
		Usuario usuario = new Usuario();

		anuncio.setTitulo("TEE");
		anuncio.setDescricao("TEDO");
		anuncio.setCategoria(categoria);
		DateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
		Date data3 = formato2.parse("30/05/2021");
		anuncio.setDataCriacao(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		anuncio.setDataExpiracao(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		anuncio.setDataFim(data3);
		//anuncio.setNotaAvaliacao(1);
		anuncio.setStatus(statusAnuncio);
		anuncio.setUsuarioAnunciante(usuario);
		
		categoria.setIdCategoria(1);
		statusAnuncio.setIdStatus(1);
		usuario.setIdUsuario(1);
		
	    anuncioRepository.save(anuncio);
	 
	 
	    mockMvc.perform(delete("/api/usuario/deletar")
	    		.contentType("application/json")
	    		.content(objectMapper.writeValueAsString(anuncio)));  
	    
		anuncioRepository.delete(anuncio);
		
		
	}
	
	@Test
	public void TestAtualizarAnuncio() throws Exception {
		
		Anuncio anuncio = new Anuncio();
		Categoria categoria = new Categoria();
		StatusAnuncio statusAnuncio = new StatusAnuncio();
		Usuario usuario = new Usuario();
		
		categoria.setIdCategoria(1);
		statusAnuncio.setIdStatus(1);
		usuario.setIdUsuario(1);
		
		anuncio.setTitulo("TEE");
		anuncio.setDescricao("TEDO");
		anuncio.setCategoria(categoria);
		DateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
		Date data3 = formato2.parse("30/05/2021");
		anuncio.setDataCriacao(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		anuncio.setDataExpiracao(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		anuncio.setDataFim(data3);
		//anuncio.setNotaAvaliacao(0);
		anuncio.setStatus(statusAnuncio);
		anuncio.setUsuarioAnunciante(usuario);
		
	    anuncioRepository.save(anuncio);
	    
	   anuncio.setTitulo("HUGO CABRET");
	   anuncio.setDescricao("A CHAVE");
	   
	   mockMvc.perform(put("/api/anuncio/atualizar")
			   .contentType("application/json")
			   .content(objectMapper.writeValueAsString(anuncio)))
	           .andExpect(status().isOk());
	    
		
	}
	
	
	
	@Test
	public void TestListaStatusAnuncio() throws Exception {
		
		mockMvc.perform(get("/api/anuncio/status/lista")
				.contentType("application/json"))
		         .andExpect(status().isOk());
	
	}
	
	
	@Test
	public void TestAnunciosPorTitulo() throws Exception {
		Anuncio anuncio = new Anuncio();
        anuncio.setTitulo("Teste");
		
		
		mockMvc.perform(get("/api/anuncio/titulo/" + anuncio.getTitulo())
				.contentType("application/json"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void TestAvaliaAnuncio() throws Exception{
		Anuncio anuncio = new Anuncio();
		anuncio.setIdAnuncio(6);
        AvaliacaoDto avalia = new AvaliacaoDto();
		avalia.setNotaAvaliacao(3);
		
		mockMvc.perform(post("/api/anuncio/" + anuncio.getIdAnuncio() + "/avaliar")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(avalia)))
		        .andExpect(status().isOk());
		
		
		
	}
	
	
	@Test
	public void TestRetornaAvalicaoAnuncio() throws Exception {
		Anuncio anuncio = new Anuncio();
		anuncio.setIdAnuncio(6);
		StatusAnuncio status = new StatusAnuncio();
		status.setIdStatus(3);
		anuncio.setNotaAvaliacao(3);
		anuncio.setStatus(status);
		
		
		mockMvc.perform(get("/api/anuncio/" + anuncio.getIdAnuncio() + "/avaliacao")
		.contentType("application/json"))
		.andExpect(status().isOk());
				
		
	}
	
	
	
	

	
	/*@Test
	public void TestCadastrarAnuncioTituloVazio() throws Exception {
		
		Anuncio anuncio = new Anuncio();
		Categoria categoria = new Categoria();
		StatusAnuncio statusAnuncio = new StatusAnuncio();
		Usuario usuario = new Usuario();
		
		categoria.setIdCategoria(1);
		statusAnuncio.setIdStatus(2);
		usuario.setIdUsuario(1);
		
		anuncio.setTitulo(null);
		anuncio.setDescricao("Livro");
		anuncio.setCategoria(categoria);
		DateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formato2.parse("11/02/2021");
		Date data2 = formato2.parse("30/06/2021");
		Date data3 = formato2.parse("30/05/2021");
		anuncio.setDataCriacao(data);
		anuncio.setDataExpiracao(data2);
		anuncio.setDataFim(data3);
		anuncio.setStatus(statusAnuncio);
		anuncio.setUsuarioAnunciante(usuario);
		
		
		mockMvc.perform(post("/api/anuncio/cadastrar")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(anuncio)))
		        .andExpect(status().isOk());
		
		
	}*/
	
	
	
	
	
	
	
	

}
