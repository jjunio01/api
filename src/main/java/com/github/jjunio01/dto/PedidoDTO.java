package com.github.jjunio01.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.github.jjunio01.model.Pedido;
import com.github.jjunio01.model.STATUS;

/**
 * @author JJunio
 *
 */
public class PedidoDTO {

	private String nomeClinte;
	private String produto;
	private double valor;
	private STATUS status;

	public PedidoDTO(Pedido novoPedido) {
		this.nomeClinte = novoPedido.getCliente().getNome();
		this.produto = novoPedido.getCarrinho().getItens().get(0).getProduto().getNome();
		this.valor = novoPedido.getValorTotal();
		this.status = novoPedido.getStatus();
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

	public STATUS getStatus() {
		return status;
	}

	public static List<PedidoDTO> converter(List<Pedido> listaPedidos) {
		return listaPedidos.stream().map(PedidoDTO::new).collect(Collectors.toList());
	}

}
