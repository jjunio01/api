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
import com.github.jjunio01.dto.FornecedorDTOFormAtualizar;
import com.github.jjunio01.dto.FornecedorDTOFormCadastrar;
import com.github.jjunio01.model.Fornecedor;
import com.github.jjunio01.repository.FornecedorRepository;

/**
 * @author JJunio
 *
 */
@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

	@Autowired
	private FornecedorRepository repositoryFornecedor;

	@PostMapping
	@Transactional
	public ResponseEntity<FornecedorDTO> inserir(@RequestBody @Valid FornecedorDTOFormCadastrar formFornecedor,
			UriComponentsBuilder uriBuilder) {

		Fornecedor novoFornecedor = formFornecedor.converter();
		repositoryFornecedor.save(novoFornecedor);

		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(novoFornecedor.getId()).toUri();
		return ResponseEntity.created(uri).body(new FornecedorDTO(novoFornecedor));
	}

	@GetMapping
	public List<FornecedorDTO> listarTodos() {
		return FornecedorDTO.converter(repositoryFornecedor.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<FornecedorDTO> recuperarPorID(@PathVariable int id) {
		Optional<Fornecedor> fornecedorConsulta = repositoryFornecedor.findById(id);
		if (fornecedorConsulta.isPresent()) {
			Fornecedor fornecedorBd = fornecedorConsulta.get();
			return ResponseEntity.ok(new FornecedorDTO(fornecedorBd));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<FornecedorDTO> deletar(@PathVariable int id) {
		Optional<Fornecedor> fornecedorConsulta = repositoryFornecedor.findById(id);
		if (fornecedorConsulta.isPresent()) {
			repositoryFornecedor.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<FornecedorDTO> atualizar(@RequestBody @Valid FornecedorDTOFormAtualizar formFornecedor,
			@PathVariable int id) {

		Optional<Fornecedor> fornecedorConsulta = repositoryFornecedor.findById(id);
		if (fornecedorConsulta.isPresent()) {
			Fornecedor fornecedorBD = fornecedorConsulta.get();
			return ResponseEntity.ok(new FornecedorDTO(formFornecedor.atualizar(fornecedorBD)));
		}
		return ResponseEntity.notFound().build();
	}

}
