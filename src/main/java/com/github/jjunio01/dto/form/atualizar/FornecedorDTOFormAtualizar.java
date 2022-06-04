package com.github.jjunio01.dto.form.atualizar;

import com.github.jjunio01.model.Fornecedor;

/**
 * @author JJunio
 *
 */
public class FornecedorDTOFormAtualizar {

	private String nomeFantasia;
	private String telefone;
	private String razaoSocial;

	public FornecedorDTOFormAtualizar(String nomeFantasia, String telefone, String razaoSocial) {

		this.nomeFantasia = nomeFantasia;
		this.telefone = telefone;
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Fornecedor atualizar(Fornecedor fornecedor) {
		fornecedor.setNomeFantasia(this.nomeFantasia);
		fornecedor.setTelefone(this.telefone);
		fornecedor.setRazaoSocial(this.razaoSocial);
		return fornecedor;
	}

}
