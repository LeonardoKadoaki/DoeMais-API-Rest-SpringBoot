package com.doemais.api.service;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.doemais.api.dto.AvaliacaoDto;
import com.doemais.api.models.Anuncio;
import com.doemais.api.models.Categoria;
import com.doemais.api.models.StatusAnuncio;
import com.doemais.api.models.Usuario;
import com.doemais.api.repository.AnuncioRepository;
import com.doemais.api.repository.CategoriaRepository;
import com.doemais.api.repository.StatusAnuncioRepository;
import com.doemais.api.repository.UsuarioRepository;
import com.doemais.api.services.AnuncioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AnuncioServiceTest {

	@Autowired
	private AnuncioService service;
	
	@Autowired
	private AnuncioRepository anuncioRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private StatusAnuncioRepository statusAnuncioRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	
	@Test
	public void TestCadastraAnuncioService() throws Exception {
		
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
		anuncio.setNotaAvaliacao(3);
		anuncio.setStatus(statusAnuncio);
		anuncio.setUsuarioAnunciante(usuario);
		 
		service.salvarAnuncio(anuncio);
		
	}
	
	@Test
	public void salvaAnuncioService() throws Exception {
		
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
		anuncio.setNotaAvaliacao(3);
		anuncio.setStatus(statusAnuncio);
		anuncio.setUsuarioAnunciante(usuario);
		
	   anuncioRepository.save(anuncio);
	   service.buscarAnuncioPorId(anuncio.getIdAnuncio());
		
	}
	
	@Test
	public void TestBuscaAnuncioId() throws Exception {
		
		anuncioRepository.findById(1);
		
	}
	
	
	@Test
	public void TestBuscaAnuncioPorUsuario() throws Exception{
		anuncioRepository.findAllByIdUsuario(1);
		
	}
	
	
	
	@Test
	public void TestAvaliacaoAnuncio() throws Exception{
		Anuncio anuncio = new Anuncio();
		service.buscarAnuncioPorId(6);
        anuncio.getNotaAvaliacao(); 	
		
	}
	
	@Test
	public void TestRegistraAvaliacao() throws Exception{
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
		anuncio.setNotaAvaliacao(3);
		anuncio.setStatus(statusAnuncio);
		anuncio.setUsuarioAnunciante(usuario);
		
		AvaliacaoDto avalia = new AvaliacaoDto();
		service.buscarAnuncioPorId(6);
        anuncio.setNotaAvaliacao(avalia.getNotaAvaliacao());
        anuncioRepository.save(anuncio);
		
	}
	
	@Test
	public void TestDeletaAnuncio() throws Exception{
		Anuncio anuncio = new Anuncio();
		anuncio.setIdAnuncio(6);
		anuncioRepository.findById(anuncio.getIdAnuncio());
		anuncioRepository.delete(anuncio);
	}
	
	
	
}
