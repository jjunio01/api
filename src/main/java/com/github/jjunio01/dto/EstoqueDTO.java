package com.github.jjunio01.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.github.jjunio01.model.Estoque;

/**
 * @author JJunio
 *
 */
public class EstoqueDTO {

	private int id;
	private int quantidade;
	private ProdutoDTO produto;
	private String nomeFornecedor;

	public EstoqueDTO(Estoque novoEstoque) {

		this.quantidade = novoEstoque.getQuantidade();
		this.produto = new ProdutoDTO(novoEstoque.getProduto());
		this.id = novoEstoque.getId();
		this.nomeFornecedor = novoEstoque.getFornecedor().getNomeFantasia();

	}

	public int getQuantidade() {
		return quantidade;
	}

	public ProdutoDTO getProduto() {
		return produto;
	}

	public int getId() {
		return id;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public static List<EstoqueDTO> converter(List<Estoque> estoque) {
		return estoque.stream().map(EstoqueDTO::new).collect(Collectors.toList());
	}

}
