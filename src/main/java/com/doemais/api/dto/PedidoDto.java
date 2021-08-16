package com.doemais.api.dto;

import com.doemais.api.models.Categoria;

import java.util.Objects;

public class PedidoDto {

	private String titulo;
	private String descricao;
	private Categoria categoria;

	public PedidoDto() {
	}

	public PedidoDto(String titulo, String descricao, Categoria categoria) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.categoria = categoria;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PedidoDto pedidoDto = (PedidoDto) o;
		return Objects.equals(titulo, pedidoDto.titulo) &&
				Objects.equals(descricao, pedidoDto.descricao) &&
				Objects.equals(categoria, pedidoDto.categoria);
	}

	@Override
	public int hashCode() {
		return Objects.hash(titulo, descricao, categoria);
	}

	@Override
	public String toString() {
		return "PedidoDto{" +
				"titulo='" + titulo + '\'' +
				", descricao='" + descricao + '\'' +
				", categoria=" + categoria +
				'}';
	}
}
