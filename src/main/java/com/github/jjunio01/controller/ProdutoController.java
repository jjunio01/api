package com.github.jjunio01.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jjunio01.dto.ProdutoDTO;
import com.github.jjunio01.dto.form.atualizar.ProdutoDTOFormAtualizar;
import com.github.jjunio01.dto.form.cadastrar.ProdutoDTOFormCadastrar;
import com.github.jjunio01.model.Produto;
import com.github.jjunio01.repository.ProdutoRepository;

/**
 * @author JJunio
 *
 */
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repositoryProduto;

	public List<ProdutoDTO> listarTodos() {
		return ProdutoDTO.converter(repositoryProduto.findAll());
	}

	public Produto inserir(ProdutoDTOFormCadastrar formProduto) {

		Produto novoProduto = formProduto.converter();
		return repositoryProduto.save(novoProduto);

	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDTO> recuperarPorId(@PathVariable int id) {
		Optional<Produto> produtoConsulta = repositoryProduto.findById(id);
		if (produtoConsulta.isPresent()) {
			Produto produtoBd = produtoConsulta.get();
			return ResponseEntity.ok(new ProdutoDTO(produtoBd));
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<ProdutoDTO> atualizar(int id, @Valid ProdutoDTOFormAtualizar formProduto) {
		Optional<Produto> produtoConsulta = repositoryProduto.findById(id);
		if (produtoConsulta.isPresent()) {
			Produto produtoAtualizado = formProduto.atualizar(produtoConsulta.get());
			repositoryProduto.saveAndFlush(produtoAtualizado);
			return ResponseEntity.ok(new ProdutoDTO(produtoAtualizado));
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<ProdutoDTO> remover(@PathVariable int id) {
		Optional<Produto> produtoConsulta = repositoryProduto.findById(id);
		if (produtoConsulta.isPresent()) {
			repositoryProduto.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
