package com.github.jjunio01.dto.form.atualizar;

import com.github.jjunio01.dto.UsuarioDTO;
import com.github.jjunio01.model.Fornecedor;

/**
 * @author JJunio
 *
 */
public class FornecedorDTOFormAtualizar {

	private String nomeFantasia;
	private String telefone;
	private String razaoSocial;
	private String cnpj;
	private UsuarioDTO usuario;

	public FornecedorDTOFormAtualizar(String nomeFantasia, String telefone, String razaoSocial, String cnpj,
			UsuarioDTO usuario) {

		this.nomeFantasia = nomeFantasia;
		this.telefone = telefone;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.usuario = usuario;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public Fornecedor atualizar(Fornecedor fornecedor) {
		fornecedor.setNomeFantasia(this.nomeFantasia);
		fornecedor.setTelefone(this.telefone);
		fornecedor.setRazaoSocial(this.razaoSocial);
		return fornecedor;
	}

}
