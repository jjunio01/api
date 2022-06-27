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

import com.github.jjunio01.dto.FornecedorDTO;
import com.github.jjunio01.dto.form.atualizar.FornecedorDTOFormAtualizar;
import com.github.jjunio01.dto.form.cadastrar.EnderecoDTOFormCadastrar;
import com.github.jjunio01.dto.form.cadastrar.FornecedorDTOFormCadastrar;
import com.github.jjunio01.model.Endereco;
import com.github.jjunio01.model.Fornecedor;
import com.github.jjunio01.model.Usuario;
import com.github.jjunio01.repository.EnderecoRepository;
import com.github.jjunio01.repository.FornecedorRepository;
import com.github.jjunio01.repository.UsuarioRepository;

/**
 * @author JJunio
 *
 */
@RestController
@RequestMapping("/fornecedores")
public class FornecedorController
		implements RestControllerInterface<FornecedorDTO, FornecedorDTOFormCadastrar, FornecedorDTOFormAtualizar> {

	@Autowired
	private FornecedorRepository repositoryFornecedor;

	@Autowired
	private UsuarioRepository repositoryUsuario;

	@Autowired
	private EnderecoRepository repositoryEndereco;

	@Override
	@GetMapping
	public List<FornecedorDTO> listarTodos() {
		return FornecedorDTO.converter(repositoryFornecedor.findAll());
	}

	@Override
	@PostMapping
	@Transactional
	public ResponseEntity<FornecedorDTO> inserir(@RequestBody @Valid FornecedorDTOFormCadastrar formFornecedor,
			UriComponentsBuilder uriBuilder) {

		Usuario novoUsuario = repositoryUsuario.save(formFornecedor.converterUsuario());
		Fornecedor novoFornecedor = formFornecedor.converter();
		novoFornecedor.setUsuario(novoUsuario);

		repositoryFornecedor.save(novoFornecedor);

		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(novoFornecedor.getId()).toUri();
		return ResponseEntity.created(uri).body(new FornecedorDTO(novoFornecedor));
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<FornecedorDTO> recuperarPorId(int id) {
		Optional<Fornecedor> fornecedorConsulta = repositoryFornecedor.findById(id);
		if (fornecedorConsulta.isPresent()) {
			Fornecedor fornecedorBd = fornecedorConsulta.get();
			return ResponseEntity.ok(new FornecedorDTO(fornecedorBd));
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<FornecedorDTO> atualizar(int id, @Valid FornecedorDTOFormAtualizar formFornecedor) {
		Optional<Fornecedor> fornecedorConsulta = repositoryFornecedor.findById(id);
		if (fornecedorConsulta.isPresent()) {
			Optional<Usuario> usuarioBD = repositoryUsuario.findById(fornecedorConsulta.get().getUsuario().getId());
			usuarioBD.get().setEmail(formFornecedor.getUsuario().getEmail());
			Fornecedor fornecedor = formFornecedor.atualizar(fornecedorConsulta.get());
			fornecedor.setUsuario(usuarioBD.get());
			repositoryUsuario.flush();
			repositoryFornecedor.flush();
			return ResponseEntity.ok(new FornecedorDTO(fornecedor));
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<FornecedorDTO> remover(int id) {
		Optional<Fornecedor> fornecedorConsulta = repositoryFornecedor.findById(id);
		if (fornecedorConsulta.isPresent()) {
			repositoryFornecedor.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}

	@PutMapping("/{id}/enderecos")
	@Transactional
	public ResponseEntity<FornecedorDTO> incluirEndereco(@PathVariable int id,
			@RequestBody @Valid EnderecoDTOFormCadastrar enderecoForm) {

		Optional<Fornecedor> fornecedorBD = repositoryFornecedor.findById(id);
		if (fornecedorBD.isPresent()) {
			Fornecedor fornecedor = fornecedorBD.get();
			Endereco enderecoBD = repositoryEndereco.save(enderecoForm.converter());
			fornecedor.adicionarEndereco(enderecoBD);
			repositoryFornecedor.saveAndFlush(fornecedor);
			return ResponseEntity.ok(new FornecedorDTO(fornecedor));
		}
		return ResponseEntity.notFound().build();

	}
}
