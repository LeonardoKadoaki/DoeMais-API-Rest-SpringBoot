package com.doemais.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.doemais.api.models.Categoria;
import com.doemais.api.repository.CategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CategoriaController categoriaController;
	
	@Test
	public void TestListaCategoria() throws Exception{
		
		mockMvc.perform(get("/api/categoria/lista")
				.contentType("application/json"))
		        .andExpect(status().isOk());
	}
	
	
	@Test
	public void TestListaCategoriaPorId() throws Exception{
		Categoria categoria = new Categoria();
		
		mockMvc.perform(get("/api/categoria/" + categoria.getIdCategoria())
				.contentType("application/json"))
		         .andExpect(status().isOk());
		
	}
	
	
	@Test
	public void TestCadastraCategoria() throws Exception {
		Categoria categoria = new  Categoria();
		
		categoria.setNome("Guilherme");
		
		mockMvc.perform(post("/api/categoria/cadastrar")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(categoria)))
		        .andExpect(status().isOk());
		
	}
	
	@Test
	public void TestRemoveCategoria() throws Exception {
		Categoria categoria  = new Categoria();
		
		categoria.setNome("Teste");
		
		categoriaRepository.save(categoria);
		
		mockMvc.perform(delete("/api/categoria/deletar")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(categoria)));
		
		categoriaRepository.delete(categoria);
		
		
	}
	
	
	@Test
	public void TestAtualizaCategoria() throws Exception{
		Categoria categoria = new Categoria();
		
		categoria.setIdCategoria(6);
		categoria.setNome("Guizao");
		
		mockMvc.perform(put("/api/categoria/atualizar")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(categoria)))
		        .andExpect(status().isOk());
			
	}
	
	/*@Test
	public void TestCadastrarCategoriaNomeVazio() throws Exception{
	 Categoria categoria = new Categoria();
	 
	 categoria.setNome(null);
     
	 mockMvc.perform(post("/api/categoria/cadastrar")
			 .contentType("application/json")
			 .content(objectMapper.writeValueAsString(categoria)))
	         .andExpect(status().isBadRequest());
	 
	}*/
	
	

}
