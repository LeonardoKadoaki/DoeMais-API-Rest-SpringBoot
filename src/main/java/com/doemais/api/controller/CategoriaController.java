package com.doemais.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.models.Categoria;
import com.doemais.api.repository.CategoriaRepository;
import com.doemais.api.services.CategoriaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api/categoria")
@Api(tags="Categoria")
public class CategoriaController {

	Logger logger = LoggerFactory.getLogger(CategoriaController.class);
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	CategoriaService categoriaService;
	
	@PersistenceContext
	private EntityManager em;
	
	@ApiOperation(value="Retorna uma lista de categorias")
	@GetMapping("/lista")
	public List<Categoria> listarCategorias(){
		return categoriaService.buscarCategorias();
	}
	
	@ApiOperation(value="Retorna a categoria pelo idCategoria")
	@GetMapping("/{idCategoria}")
	public Categoria consultarCategoriaPorId(@PathVariable(value="idCategoria") long idCategoria) throws EntidadeNaoEncontradaException{
		return categoriaService.buscarCategoriaPorId(idCategoria);
	}
	
	@ApiOperation(value="Cadastra uma categoria")
	@PostMapping
	public Categoria cadastrarCategoria(@RequestBody @Valid Categoria categoria) {
		return categoriaService.cadastrarCategoria(categoria);
	}
	
	@ApiOperation(value="Deleta uma categoria")
	@DeleteMapping
	public void deletarCategoria(@RequestBody @Valid Categoria categoria) throws EntidadeNaoEncontradaException {
		categoriaService.deletarCategoria(categoria);
	}
	
	@ApiOperation(value="Atualiza uma categoria")
	@PutMapping
	public Categoria atualizarCategoria(@RequestBody @Valid Categoria categoria) {
		return categoriaService.cadastrarCategoria(categoria);
	}
	 
}
