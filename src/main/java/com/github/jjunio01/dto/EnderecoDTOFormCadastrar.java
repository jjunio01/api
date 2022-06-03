package com.github.jjunio01.dto;

import javax.validation.constraints.NotNull;

import com.github.jjunio01.model.Endereco;

/**
 * @author JJunio
 *
 */
public class EnderecoDTOFormCadastrar {

	@NotNull
	private String cidade;

	@NotNull
	private String bairro;

	@NotNull
	private String rua;

	@NotNull
	private String numero;

	private String referencia;

	public EnderecoDTOFormCadastrar(@NotNull String cidade, @NotNull String bairro, @NotNull String rua,
			@NotNull String numero, @NotNull String referencia) {
		super();
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
		this.referencia = referencia;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Endereco converter() {
		return new Endereco(this.cidade, this.bairro, this.rua, this.numero, this.referencia);
	}

}
