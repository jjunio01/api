package com.github.jjunio01.dto.form.cadastrar;

import com.github.jjunio01.model.Estoque;

/**
 * @author JJunio
 *
 */
public class EstoqueDTOFormCadastrar implements DTOFormCadastrar<Estoque> {

	private int id;

	private int quantidade;

	private ProdutoDTOFormCadastrar produto;

	public EstoqueDTOFormCadastrar(int id, int quantidade, ProdutoDTOFormCadastrar novoProduto) {
		this.id = id;
		this.quantidade = quantidade;
		this.produto = novoProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public ProdutoDTOFormCadastrar getProduto() {
		return produto;
	}

	public int getId() {
		return id;
	}

	@Override
	public Estoque converter() {

		return new Estoque(this.quantidade, produto.converter());
	}

}
