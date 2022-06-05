package com.github.jjunio01.dto.form.cadastrar;

import java.util.List;

/**
 * @author JJunio
 *
 */
public class PedidoDTOFormCadastrar {

	private int idCliente;
	private List<ItemProdutoDTOForm> produtos;
	private int idEndereco;

	public int getIdCliente() {
		return idCliente;
	}

	public int getIdEndereco() {
		return idEndereco;
	}

	public List<ItemProdutoDTOForm> getProdutos() {
		return produtos;
	}
}
