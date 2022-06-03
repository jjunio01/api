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

import com.github.jjunio01.dto.ProdutoDTO;
import com.github.jjunio01.dto.ProdutoDTOFormAtualizar;
import com.github.jjunio01.dto.ProdutoDTOFormCadastrar;
import com.github.jjunio01.model.Produto;
import com.github.jjunio01.repository.ProdutoRepository;

/**
 * @author JJunio
 *
 */
@RestController
@RequestMapping("/produtos")
public class ControllerProduto {

	@Autowired
	private ProdutoRepository repositoryProduto;

	@GetMapping
	public List<ProdutoDTO> listarTodos() {
		return ProdutoDTO.converter(repositoryProduto.findAll());
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ProdutoDTO> inserir(@RequestBody @Valid ProdutoDTOFormCadastrar formProduto,
			UriComponentsBuilder uriBuilder) {

		Produto novoProduto = formProduto.converter();
		repositoryProduto.save(novoProduto);

		URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(novoProduto.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProdutoDTO(novoProduto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDTO> recuperarPorID(@PathVariable int id) {
		Optional<Produto> produtoConsulta = repositoryProduto.findById(id);
		if (produtoConsulta.isPresent()) {
			Produto produtoBd = produtoConsulta.get();
			return ResponseEntity.ok(new ProdutoDTO(produtoBd));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<ProdutoDTO> deletar(@PathVariable int id) {
		Optional<Produto> produtoConsulta = repositoryProduto.findById(id);
		if (produtoConsulta.isPresent()) {
			repositoryProduto.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProdutoDTO> atualizar(@RequestBody @Valid ProdutoDTOFormAtualizar formProduto,
			@PathVariable int id) {

		Optional<Produto> produtoConsulta = repositoryProduto.findById(id);
		if (produtoConsulta.isPresent()) {
			Produto produtoBD = formProduto.atualizar(repositoryProduto, id);
			return ResponseEntity.ok(new ProdutoDTO(produtoBD));
		}
		return ResponseEntity.notFound().build();
	}

}
