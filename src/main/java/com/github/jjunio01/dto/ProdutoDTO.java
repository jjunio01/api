package com.github.jjunio01.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.github.jjunio01.model.Produto;

/**
 * @author JJunio
 *
 */
public class ProdutoDTO {

	private int id;
	private double valor;
	private String marca;
	private String nome;
	private String descricao;

	public ProdutoDTO(Produto novoProduto) {

		this.id = novoProduto.getId();
		this.valor = novoProduto.getValor();
		this.marca = novoProduto.getMarca();
		this.nome = novoProduto.getNome();
		this.descricao = novoProduto.getDescricao();

	}

	public int getId() {
		return id;
	}

	public double getValor() {
		return valor;
	}

	public String getMarca() {
		return marca;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public static List<ProdutoDTO> converter(List<Produto> produtos) {

		return produtos.stream().map(ProdutoDTO::new).collect(Collectors.toList());
	}

}
