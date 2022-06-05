package com.github.jjunio01.dto.form.cadastrar;

import com.github.jjunio01.model.ItemProduto;
import com.github.jjunio01.model.Produto;

/**
 * @author JJunio
 *
 */
public class ItemProdutoDTOForm {

	private int idProduto;
	private int quantidade;

	public ItemProdutoDTOForm(int idProduto, int quantidade) {

		this.idProduto = idProduto;
		this.quantidade = quantidade;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public ItemProduto converter(Produto produto) {
		return new ItemProduto(produto, this.quantidade);
	}

}
