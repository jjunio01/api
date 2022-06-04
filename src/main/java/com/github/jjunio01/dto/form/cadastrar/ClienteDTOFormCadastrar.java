package com.github.jjunio01.dto.form.cadastrar;

import javax.validation.constraints.NotNull;

import com.github.jjunio01.model.Cliente;

/**
 * @author JJunio
 *
 */
public class ClienteDTOFormCadastrar implements DTOFormCadastrar<Cliente> {

	@NotNull
	private String nome;
	@NotNull
	private String codigo;
	@NotNull
	private String senha;
	@NotNull
	private String email;

	private String telefone;

	public ClienteDTOFormCadastrar(String nome, String codigo, String senha, String email, String telefone) {
		this.nome = nome;
		this.codigo = codigo;
		this.senha = senha;
		this.email = email;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public Cliente converter() {
		return new Cliente(this.codigo, this.nome, this.senha, this.email, this.telefone);
	}

}
