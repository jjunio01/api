package com.github.jjunio01.dto;

import com.github.jjunio01.model.Endereco;

/**
 * @author JJunio
 *
 */
public class EnderecoDTO {

	private int id;
	private String cidade;
	private String bairro;
	private String rua;
	private String numero;
	private String referencia;

	public EnderecoDTO(Endereco endereco) {
		super();
		this.cidade = endereco.getCidade();
		this.bairro = endereco.getBairro();
		this.rua = endereco.getRua();
		this.numero = endereco.getNumero();
		this.referencia = endereco.getReferencia();
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

	public int getId() {
		return id;
	}

}
