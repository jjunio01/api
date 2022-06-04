package com.github.jjunio01.dto.form.cadastrar;

import javax.validation.constraints.NotNull;

import com.github.jjunio01.model.Produto;

/**
 * @author JJunio
 *
 */
public class ProdutoDTOFormCadastrar implements DTOFormCadastrar<Produto> {

	@NotNull
	private String nome;
	@NotNull
	private double valor;

	private String marca;

	private String descricao;

	public ProdutoDTOFormCadastrar(String nome, double valor, String marca, String descricao) {
		this.nome = nome;
		this.valor = valor;
		this.marca = marca;
		this.descricao = descricao;
	}

	public ProdutoDTOFormCadastrar() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

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

	@Override
	public Produto converter() {

		return new Produto(this.nome, this.valor, this.marca, this.descricao);
	}

}
