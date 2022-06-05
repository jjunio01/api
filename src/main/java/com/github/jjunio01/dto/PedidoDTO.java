package com.github.jjunio01.dto;

import com.github.jjunio01.model.Pedido;

/**
 * @author JJunio
 *
 */
public class PedidoDTO {

	private String nomeClinte;
	private String produto;
	private double valor;

	public PedidoDTO(Pedido novoPedido) {
		this.nomeClinte = novoPedido.getCliente().getNome();
		this.produto = novoPedido.getCarrinho().getItens().get(0).getProduto().getNome();
		this.valor = novoPedido.getValorTotal();
	}

	public String getNomeClinte() {
		return nomeClinte;
	}

	public String getProduto() {
		return produto;
	}

	public double getValor() {
		return valor;
	}

}
