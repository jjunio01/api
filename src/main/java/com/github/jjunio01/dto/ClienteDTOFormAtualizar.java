package com.github.jjunio01.dto;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.github.jjunio01.model.Cliente;
import com.github.jjunio01.repository.ClienteRepository;

/**
 * @author JJunio
 *
 */
public class ClienteDTOFormAtualizar {

	@NotNull
	private String nome;
	@NotNull
	private String senha;
	@NotNull
	private String email;

	private String telefone;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Cliente atualizar(ClienteRepository repositoryCliente, int id) {

		Optional<Cliente> clienteConsulta = repositoryCliente.findById(id);
		Cliente clienteBD = null;
		if (clienteConsulta.isPresent()) {
			clienteBD = clienteConsulta.get();
			clienteBD.setNome(this.nome);
			clienteBD.setSenha(this.senha);
			clienteBD.setEmail(this.email);
			clienteBD.setTelefone(this.telefone);
			return clienteBD;
		}
		return clienteBD;
	}

}
