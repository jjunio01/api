package com.github.jjunio01.dto;

import javax.validation.constraints.NotNull;

import com.github.jjunio01.model.Fornecedor;

/**
 * @author JJunio
 *
 */
public class FornecedorDTOFormCadastrar {

	@NotNull
	private String cnpj;
	
	@NotNull
	private String nomeFantasia;
	
	private String razaoSocial;
	private String telefone;

	public FornecedorDTOFormCadastrar(String cnpj, String nomeFantasia, String razaoSocial, String telefone) {
		this.cnpj = cnpj;
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.telefone = telefone;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Fornecedor converter() {
		return new Fornecedor(this.nomeFantasia, this.razaoSocial, this.cnpj, this.telefone);
	}
}
