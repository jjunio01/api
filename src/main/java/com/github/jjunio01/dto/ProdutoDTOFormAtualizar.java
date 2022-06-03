package com.github.jjunio01.dto;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.github.jjunio01.model.Produto;
import com.github.jjunio01.repository.ProdutoRepository;

/**
 * @author JJunio
 *
 */
public class ProdutoDTOFormAtualizar {

	@NotNull
	private double valor;

	private String marca;

	private String descricao;

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Produto atualizar(ProdutoRepository repositoryProduto, int id) {
		Produto produtoBD = null;
		Optional<Produto> produtoConsulta = repositoryProduto.findById(id);

		if (produtoConsulta.isPresent()) {
			produtoBD = produtoConsulta.get();
			produtoBD.setDescricao(this.descricao);
			produtoBD.setMarca(this.marca);
			produtoBD.setValor(this.valor);
		}

		return produtoBD;
	}

}
