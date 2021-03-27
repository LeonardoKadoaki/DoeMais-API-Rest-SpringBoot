package com.doemais.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.models.Categoria;
import com.doemais.api.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	public List<Categoria> buscarCategorias(){
		return categoriaRepository.findAll();
	}
	
	public Categoria buscarCategoriaPorId(long idCategoria) throws EntidadeNaoEncontradaException{
		return categoriaRepository.findByIdCategoria(idCategoria).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Categoria id %d não encontrada", idCategoria)));
	}
	
	public Categoria cadastrarCategoria(Categoria categoria){
		validaInformacoesCategoria(categoria);
		return categoriaRepository.save(categoria);
	}
	
	protected void validaInformacoesCategoria(Categoria categoria) {
		
		if(categoria.getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("O nome da categoria não pode ser vazio");
		}
		if(categoria.getMoedasCategoria() == 0) {
			throw new IllegalArgumentException("A categoria precisa de moedas");
		}
	}
	
	public void deletarCategoria(long idCategoria) throws EntidadeNaoEncontradaException{
		Categoria categoria = buscarCategoriaPorId(idCategoria);
		categoriaRepository.delete(categoria);
	}
	
}
