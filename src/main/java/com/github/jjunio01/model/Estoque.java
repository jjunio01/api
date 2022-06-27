package com.github.jjunio01.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @author JJunio
 *
 */
@Entity(name = "ESTOQUE")
public class Estoque implements Serializable {

	private static final long serialVersionUID = 481075845031272137L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estoque")
	private int id;

	@OneToOne(fetch = FetchType.EAGER,  cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_produto")
	private Produto produto;

	@ManyToOne
	private Fornecedor fornecedor;

	private int quantidade;

	public Estoque() {

	}

	public Estoque(int quantidade, Produto novoProduto) {
		this.quantidade = quantidade;
		this.produto = novoProduto;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void retirarEstoque(int quantidade) {
		setQuantidade(this.quantidade - quantidade);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + quantidade;
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
		Estoque other = (Estoque) obj;
		if (id != other.id)
			return false;
		if (quantidade != other.quantidade)
			return false;
		return true;
	}

}
