package com.github.jjunio01.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.github.jjunio01.model.Estoque;

/**
 * @author JJunio
 *
 */
public class EstoqueDTO {

	private int quantidade;
	private ProdutoDTO produto;

	public EstoqueDTO(Estoque novoEstoque) {

		this.quantidade = novoEstoque.getQuantidade();
		this.produto = new ProdutoDTO(novoEstoque.getProduto());

	}

	public int getQuantidade() {
		return quantidade;
	}

	public ProdutoDTO getProduto() {
		return produto;
	}

	public static List<EstoqueDTO> converter(List<Estoque> estoque) {
		return estoque.stream().map(EstoqueDTO::new).collect(Collectors.toList());
	}

}
