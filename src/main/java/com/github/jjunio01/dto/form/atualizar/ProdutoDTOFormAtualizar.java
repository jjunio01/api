package com.github.jjunio01.dto.form.atualizar;

import com.github.jjunio01.model.Produto;

/**
 * @author JJunio
 *
 */
public class ProdutoDTOFormAtualizar {

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

	public Produto atualizar(Produto produtoBD) {

		produtoBD.setValor(this.valor);

		return produtoBD;
	}

}
