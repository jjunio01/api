package com.github.jjunio01.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author JJunio
 *
 */
@Entity(name = "CARRINHO")
public class Carrinho implements Serializable {

	private static final long serialVersionUID = -1535690890808810531L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_carrinho")
	private int id;

	@Column(name = "valor_total")
	private double valorTotal;

	@OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemProduto> itens = new ArrayList<ItemProduto>();;

	@OneToOne(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Pedido pedido;

	public Carrinho() {

	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<ItemProduto> getItens() {
		return itens;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void adicionarItem(ItemProduto novoItem) {
		this.itens.add(novoItem);
		novoItem.setCarrinho(this);
	}

	public void removerItem(ItemProduto item) {
		this.itens.remove(item);
		item.setCarrinho(null);
	}

	public void atualizaValorTotal() {
		setValorTotal(this.itens.stream().reduce(0.0, (somaParcial, item) -> item.getQuantidade() * item.getValor(),
				Double::sum));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(valorTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrinho other = (Carrinho) obj;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(valorTotal) != Double.doubleToLongBits(other.valorTotal))
			return false;
		return true;
	}

}
