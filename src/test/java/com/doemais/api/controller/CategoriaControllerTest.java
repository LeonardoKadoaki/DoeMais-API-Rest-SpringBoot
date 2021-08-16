package com.doemais.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.models.Categoria;
import com.doemais.api.services.CategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class CategoriaControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private CategoriaService categoriaService;
	

	private Categoria categoria = new Categoria();
	private Categoria category = new Categoria();
	private List<Categoria> categoriaLista = new ArrayList<>(); 

	
	@BeforeEach
	void Configuracao() {
		
	categoriaLista = new ArrayList<Categoria>();
	
	
	categoria = new Categoria();
	categoria.setIdCategoria(1);
	categoria.setMoedasCategoria(300);
	categoria.setNome("Moveis");
	
	
	}
	
	
	
	@Test
	 void CadastraCategoriaTest() throws Exception {
		
		Mockito.when(categoriaService.cadastrarCategoria(categoria)).thenReturn(categoria);
		
		mockMvc.perform(post("/api/categoria")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(categoria)))
		        .andExpect(status().isOk());
		
	}
	
	
	@Test
	void ListaCategoriaTest() throws Exception{
		
		Mockito.when(categoriaService.buscarCategorias()).thenReturn(categoriaLista);
		
		mockMvc.perform(get("/api/categoria/lista")
				.contentType("application/json"))
		        .andExpect(status().isOk());
	}
	
	
	
	@Test
	 void ListaCategoriaPorIdTest() throws Exception{
		
		Mockito.when(categoriaService.buscarCategoriaPorId(categoria.getIdCategoria())).thenReturn(categoria);
		
		mockMvc.perform(get("/api/categoria/" + categoria.getIdCategoria())
				.contentType("application/json"))
		         .andExpect(status().isOk());
		
	}
	
	@Test
	void RemoveCategoriaNaoEncontradaTest() throws Exception {
		
		Mockito.doThrow(new EntidadeNaoEncontradaException(String.format("Categoria id %d não encontrada",
				categoria.getIdCategoria()))).when(categoriaService).deletarCategoria(categoria.getIdCategoria());
		
		mockMvc.perform(delete("/api/categoria/" + categoria.getIdCategoria())
				.contentType("application/json"))
                .andExpect(status().isNotFound());
	}
	

	@Test
	 void AtualizaCategoriaTest() throws Exception{
		
		Mockito.when(categoriaService.cadastrarCategoria(categoria)).thenReturn(categoria);
		
		mockMvc.perform(put("/api/categoria/" + categoria.getIdCategoria())
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(categoria)))
		        .andExpect(status().isOk());
			
	}
	

	@Test
	 void RemoveCategoriaTest() throws Exception {
		
		Mockito.doNothing().when(categoriaService).deletarCategoria(categoria.getIdCategoria());
		
		mockMvc.perform(delete("/api/categoria/" + categoria.getIdCategoria())
				.contentType("application/json"))
                .andExpect(status().isOk());
	}
	
	
	
	
	
	
	
	

}
