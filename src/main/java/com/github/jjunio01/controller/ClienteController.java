package com.github.jjunio01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jjunio01.dto.ClienteDTO;
import com.github.jjunio01.repository.ClienteRepository;

/**
 * @author JJunio
 *
 */
@RestController
public class ClienteController {

	@Autowired
	private ClienteRepository repositoryCliente;

	@RequestMapping("/clientes")

	public List<ClienteDTO> listarTodos() {
		return ClienteDTO.converterCliente(repositoryCliente.findAll());
	}

}
