package com.github.jjunio01.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.github.jjunio01.model.Cliente;

/**
 * @author JJunio
 *
 */
public class ClienteDTO {

	private int id;
	private String codigo;
	private String nome;
	private String email;
	private String telefone;
	private int enderecoId;

	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.codigo = cliente.getCodigo();
		this.nome = cliente.getNome();
		this.email = cliente.getUsuario().getEmail();
		this.telefone = cliente.getTelefone();
		if (cliente.getEnderecos() != null && !cliente.getEnderecos().isEmpty()) {
			this.enderecoId = cliente.getEnderecos().get(0).getId();
		} else {
			this.enderecoId = 0;
		}
	}

	public int getId() {
		return id;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public int getEnderecoId() {
		return enderecoId;
	}

	public static List<ClienteDTO> converterCliente(List<Cliente> clientes) {
		return clientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
	}

}
