package com.github.jjunio01.model;

import java.util.List;

/**
 * @author JJunio
 *
 */
public class Carrinho {

	private int id;
	private double valroTotal;
	private List<ItemProduto> items;

	public int getId() {
		return id;
	}

	public double getValroTotal() {
		return valroTotal;
	}

	public void setValroTotal(double valroTotal) {
		this.valroTotal = valroTotal;
	}

	public List<ItemProduto> getItems() {
		return items;
	}

	public void setItems(List<ItemProduto> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valroTotal);
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
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (Double.doubleToLongBits(valroTotal) != Double.doubleToLongBits(other.valroTotal))
			return false;
		return true;
	}

}
