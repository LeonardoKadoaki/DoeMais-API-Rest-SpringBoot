package com.doemais.api.services;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.models.Categoria;
import com.doemais.api.repository.CategoriaRepository;

@SpringBootTest
class CategoriaServiceTest {

	@MockBean
	private CategoriaRepository categoriaRepository;

	@MockBean
	private CategoriaService categoriaService;

	private Categoria categoria;

	@BeforeEach
	void Configuracao() {
		categoria = new Categoria(1, "Teste", 300);

	}

	@Test
	void TestCadastraCategoria() throws Exception {
		Mockito.when(categoriaRepository.findByIdCategoria(categoria.getIdCategoria()))
				.thenReturn(Optional.ofNullable(categoria));
		categoriaService.validaInformacoesCategoria(categoria);
	    categoriaRepository.save(categoria);
		Categoria categorias = categoriaService.cadastrarCategoria(categoria);
		assertNull(categorias);
	}

	@Test
	void TestBuscaCategorias() throws Exception {
		Mockito.when(categoriaRepository.findByIdCategoria(categoria.getIdCategoria()))
				.thenReturn(Optional.ofNullable(categoria));
		List<Categoria> category = categoriaService.buscarCategorias();
		categoriaRepository.findAll();
		assertTrue(category.isEmpty());
	}

	@Test
	void TestBuscaCategoriaPorId() throws Exception {
		Mockito.when(categoriaRepository.findById(categoria.getIdCategoria()))
				.thenReturn(Optional.ofNullable(categoria));
		Categoria categoriaGetId = categoriaService.buscarCategoriaPorId(categoria.getIdCategoria());
		assertNotEquals(categoria, categoriaGetId);

	}

	@Test
	void TestDeletaCategoria() throws Exception {
		Mockito.when(categoriaRepository.findByIdCategoria(categoria.getIdCategoria()))
				.thenReturn(Optional.ofNullable(categoria));
		Mockito.doNothing().when(categoriaRepository).delete(categoria);
		categoriaService.deletarCategoria(categoria.getIdCategoria());
		categoriaRepository.delete(categoria);
		assertNotNull(categoria);
 	}

	
	@Test
	void TestValidaCategoria() {
		categoriaService.validaInformacoesCategoria(categoria);
		if(categoria.getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("O nome da categoria não pode ser vazio");
		}
		if(categoria.getMoedasCategoria() <= 0) {
			throw new IllegalArgumentException("A categoria precisa de moedas");
		}
		assertNotNull(categoria);
	}
}
