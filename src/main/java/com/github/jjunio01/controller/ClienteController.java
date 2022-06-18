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
import com.github.jjunio01.dto.form.atualizar.ClienteDTOFormAtualizar;
import com.github.jjunio01.dto.form.cadastrar.ClienteDTOFormCadastrar;
import com.github.jjunio01.dto.form.cadastrar.EnderecoDTOFormCadastrar;
import com.github.jjunio01.model.Cliente;
import com.github.jjunio01.model.Endereco;
import com.github.jjunio01.model.Usuario;
import com.github.jjunio01.repository.ClienteRepository;
import com.github.jjunio01.repository.EnderecoRepository;
import com.github.jjunio01.repository.UsuarioRepository;

/**
 * @author JJunio
 *
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController
		implements RestControllerInterface<ClienteDTO, ClienteDTOFormCadastrar, ClienteDTOFormAtualizar> {

	@Autowired
	private ClienteRepository repositoryCliente;

	@Autowired
	private EnderecoRepository repositoryEndereco;

	@Autowired
	private UsuarioRepository repositoryUsuario;

	@Override
	@GetMapping
	public List<ClienteDTO> listarTodos() {
		return ClienteDTO.converterCliente(repositoryCliente.findAll());
	}

	@Override
	@PostMapping
	@Transactional
	public ResponseEntity<ClienteDTO> inserir(@Valid ClienteDTOFormCadastrar clienteForm,
			UriComponentsBuilder uriBuilder) {
		Usuario novoUsuario = repositoryUsuario.save(clienteForm.converterUsuario());
		Cliente novoCliente = clienteForm.converter();
		novoCliente.setUsuario(novoUsuario);
		repositoryCliente.save(novoCliente);
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(novoCliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDTO(novoCliente));
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> recuperarPorId(@PathVariable int id) {
		Optional<Cliente> cliente = repositoryCliente.findById(id);
		if (cliente.isPresent()) {
			return ResponseEntity.ok(new ClienteDTO(cliente.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ClienteDTO> atualizar(@PathVariable int id,
			@Valid ClienteDTOFormAtualizar clienteFormAtualizar) {
		Optional<Cliente> clienteBD = repositoryCliente.findById(id);
		if (clienteBD.isPresent()) {
			Optional<Usuario> usuarioBD = repositoryUsuario.findById(clienteBD.get().getUsuario().getId());
			Cliente cliente = clienteFormAtualizar.atualizar(clienteBD.get());
			usuarioBD.get().setEmail(clienteFormAtualizar.getUsuario().getEmail());
			cliente.setUsuario(usuarioBD.get());
			repositoryUsuario.flush();
			repositoryCliente.flush();
			return ResponseEntity.ok(new ClienteDTO(cliente));
		}
		return ResponseEntity.notFound().build();
	}

	@Override
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

	@PutMapping("/{id}/enderecos")
	@Transactional
	public ResponseEntity<ClienteDTO> incluirEndereco(@PathVariable int id,
			@RequestBody @Valid EnderecoDTOFormCadastrar enderecoForm) {

		Optional<Cliente> clienteBD = repositoryCliente.findById(id);
		if (clienteBD.isPresent()) {
			Cliente cliente = clienteBD.get();
			Endereco enderecoBD = repositoryEndereco.save(enderecoForm.converter());
			cliente.adicionarEndereco(enderecoBD);
			repositoryCliente.saveAndFlush(cliente);
			return ResponseEntity.ok(new ClienteDTO(cliente));
		}
		return ResponseEntity.notFound().build();

	}

	@GetMapping("/{id}/enderecos")
	public ResponseEntity<ClienteDTO> recuperarEndereco(@PathVariable Integer id) {

		// Optional<Cliente> clienteBD = repositoryCliente.findByEnderecosCliente(id);
		// if (clienteBD.isPresent()) {
		// Cliente cliente = clienteBD.get();
		// return ResponseEntity.ok(new ClienteDTO(cliente));
		// }
		return ResponseEntity.notFound().build();

	}

}
