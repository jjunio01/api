package com.github.jjunio01.dto.form.cadastrar;

import javax.validation.constraints.NotNull;

import com.github.jjunio01.model.Fornecedor;

/**
 * @author JJunio
 *
 */
public class FornecedorDTOFormCadastrar implements DTOFormCadastrar<Fornecedor> {

	@NotNull
	private String cnpj;

	@NotNull
	private String nomeFantasia;

	private String razaoSocial;
	private String telefone;
	private String senha;

	public FornecedorDTOFormCadastrar(String cnpj, String nomeFantasia, String razaoSocial, String telefone,
			String senha) {
		this.cnpj = cnpj;
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.telefone = telefone;
		this.senha = senha;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public Fornecedor converter() {
		return new Fornecedor(this.nomeFantasia, this.razaoSocial, this.cnpj, this.telefone, this.senha);
	}
}
