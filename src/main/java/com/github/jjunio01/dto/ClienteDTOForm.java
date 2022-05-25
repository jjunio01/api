package com.github.jjunio01.dto;

import javax.validation.constraints.NotNull;

import com.github.jjunio01.model.Cliente;


/**
 * @author JJunio
 *
 */
public class ClienteDTOForm {
	
	@NotNull
	private String nome;
	@NotNull
	private String codigo;
	@NotNull
	private String senha;
	@NotNull
	private String email;
	
	
	public ClienteDTOForm(String nome, String codigo, String senha, String email) {
		this.nome = nome;
		this.codigo = codigo;
		this.senha = senha;
		this.email = email;
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
	public Cliente converter() {
		return new Cliente(this.codigo, this.nome, this.senha, this.email);
	}
	
	

}
