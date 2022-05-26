package com.github.jjunio01.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.jjunio01.dto.ClienteDTO;
import com.github.jjunio01.dto.ClienteDTOFormAtualizar;
import com.github.jjunio01.dto.ClienteDTOFormCadastrar;
import com.github.jjunio01.model.Cliente;
import com.github.jjunio01.repository.ClienteRepository;

/**
 * @author JJunio
 *
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository repositoryCliente;

	@GetMapping
	public List<ClienteDTO> listarTodos() {
		return ClienteDTO.converterCliente(repositoryCliente.findAll());
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ClienteDTO> inserir(@RequestBody @Valid ClienteDTOFormCadastrar clienteForm,
			UriComponentsBuilder uriBuilder) {
		Cliente novoCliente = clienteForm.converter();
		repositoryCliente.save(novoCliente);
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(novoCliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDTO(novoCliente));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> recuperarPorId(@PathVariable int id, UriComponentsBuilder uriBuilder) {
		Optional<Cliente> cliente = repositoryCliente.findById(id);
		if (cliente.isPresent()) {
			return ResponseEntity.ok(new ClienteDTO(cliente.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ClienteDTO> atualizar(@PathVariable int id,
			@RequestBody @Valid ClienteDTOFormAtualizar clienteFormAtualizar) {

		Optional<Cliente> clienteBD = repositoryCliente.findById(id);
		if (clienteBD.isPresent()) {
			Cliente cliente = clienteFormAtualizar.atualizar(repositoryCliente, id);
			return ResponseEntity.ok(new ClienteDTO(cliente));
		}
		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<ClienteDTO> remover(@PathVariable int id) {
		Optional<Cliente> cliente = repositoryCliente.findById(id);
		if (cliente.isPresent()) {
			repositoryCliente.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
