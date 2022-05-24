package com.github.jjunio01.model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JJunio
 *
 */
public class ClienteDTO {
	
	private int id;
	private String codigo;
	private String nome;
	private String email;
	
	
	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.codigo = cliente.getCodigo();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
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
	
	public static List<ClienteDTO> converterCliente(List<Cliente> clientes) {
		return clientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
	}
	

}
