package com.github.jjunio01.dto.form.cadastrar;

import java.util.List;

import com.github.jjunio01.model.Carrinho;

/**
 * @author JJunio
 *
 */
public class CarrinhoDTOFRomCadastrar {

	private List<ItemProdutoDTOForm> items;

	public CarrinhoDTOFRomCadastrar(List<ItemProdutoDTOForm> items) {
		this.items = items;
	}

	public List<ItemProdutoDTOForm> getItems() {
		return items;
	}

	public Carrinho converter() {
		return new Carrinho();
	}

}
