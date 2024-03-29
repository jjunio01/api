package com.github.jjunio01.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.github.jjunio01.dto.EstoqueDTO;
import com.github.jjunio01.dto.form.atualizar.EstoqueDTOFormAtualizar;
import com.github.jjunio01.dto.form.cadastrar.EstoqueDTOFormCadastrar;
import com.github.jjunio01.model.Estoque;
import com.github.jjunio01.model.Fornecedor;
import com.github.jjunio01.model.Produto;
import com.github.jjunio01.repository.EstoqueRepository;
import com.github.jjunio01.repository.FornecedorRepository;

/**
 * @author JJunio
 *
 */
@RestController
@RequestMapping("/fornecedores")
public class EstoqueController implements RestControllerInterface<EstoqueDTO, EstoqueDTOFormCadastrar, Integer> {

	@Autowired
	private FornecedorRepository repositoryFornecedor;

	@Autowired
	private EstoqueRepository repositoryEstoque;

	@Autowired
	private ProdutoController controllerProduto;

	@Override
	@GetMapping("/estoques")
	public List<EstoqueDTO> listarTodos() {
		return EstoqueDTO.converter(repositoryEstoque.findAll());
	}

	@Override
	@PostMapping("/estoques")
	@Transactional
	public ResponseEntity<EstoqueDTO> inserir(@Valid EstoqueDTOFormCadastrar formEstoqueDTOCadastrar,
			UriComponentsBuilder uriBuilder) {

		Optional<Fornecedor> fornecedorConsultaBD = repositoryFornecedor.findById(formEstoqueDTOCadastrar.getId());

		if (fornecedorConsultaBD.isPresent()) {

			Produto novoProduto = controllerProduto.inserir(formEstoqueDTOCadastrar.getProduto());
			Estoque novoEstoque = new Estoque(formEstoqueDTOCadastrar.getQuantidade(), novoProduto);
			Fornecedor fornecedorBD = fornecedorConsultaBD.get();
			fornecedorBD.adicionarEstoque(novoEstoque);

			repositoryEstoque.save(novoEstoque);

			URI uri = uriBuilder.path("/estoques/{id}").buildAndExpand(novoEstoque.getId()).toUri();
			return ResponseEntity.created(uri).body(new EstoqueDTO(novoEstoque));

		}
		return ResponseEntity.notFound().build();

	}

	@Override
	@GetMapping("estoques/{id}")
	public ResponseEntity<EstoqueDTO> recuperarPorId(@PathVariable int id) {
		Optional<Estoque> consultaEstoqueBD = repositoryEstoque.findById(id);

		if (consultaEstoqueBD.isPresent()) {
			Estoque estoqueBD = consultaEstoqueBD.get();
			return ResponseEntity.ok(new EstoqueDTO(estoqueBD));
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	@PutMapping("/estoques/{id}")
	@Transactional
	public ResponseEntity<EstoqueDTO> atualizar(@PathVariable int id, @RequestBody Integer quantidade) {

		if (quantidade < 0) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

		Optional<Estoque> consultaEstoqueBD = repositoryEstoque.findById(id);

		if (consultaEstoqueBD.isPresent()) {
			Estoque estoqueBD = consultaEstoqueBD.get();
			estoqueBD.setQuantidade(quantidade);
			repositoryEstoque.flush();
			return ResponseEntity.ok(new EstoqueDTO(estoqueBD));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/estoques/produto/{id}")
	@Transactional
	public ResponseEntity<EstoqueDTO> atualizarPorIdProduto(@PathVariable int id,
			@RequestBody EstoqueDTOFormAtualizar formEstoqueAtualizar) {

		if (formEstoqueAtualizar.getQuantidade() < 0) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

		Optional<Estoque> estoqueConsulta = repositoryEstoque.findByProdutoId(id);

		if (estoqueConsulta.isPresent()) {

			controllerProduto.atualizar(id, formEstoqueAtualizar.getProduto());
			Estoque estoque = formEstoqueAtualizar.atualizarQuantidade(estoqueConsulta.get());
			repositoryEstoque.flush();

			return ResponseEntity.ok(new EstoqueDTO(estoque));
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	@DeleteMapping("/estoques/{id}")
	@Transactional
	public ResponseEntity<EstoqueDTO> remover(@PathVariable int id) {
		Optional<Estoque> consultaEstoqueBD = repositoryEstoque.findById(id);

		if (consultaEstoqueBD.isPresent()) {
			repositoryEstoque.delete(consultaEstoqueBD.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}

	@GetMapping("/{id}/estoques")
	public List<EstoqueDTO> listarTodosPorIdFornecedor(@PathVariable int id) {
		return EstoqueDTO.converter(repositoryEstoque.findByFornecedorId(id));
	}

	@GetMapping("/{nomeFornecedor}/estoque")
	public List<EstoqueDTO> listarTodosPorNomeFornecedor(@PathVariable String nomeFornecedor) {
		return EstoqueDTO.converter(repositoryEstoque.findByFornecedorNomeFantasia(nomeFornecedor));
	}

	@GetMapping("/{nomeProduto}/produto")
	public List<EstoqueDTO> listarPorNomeProduto(@PathVariable String nomeProduto) {
		return EstoqueDTO.converter(repositoryEstoque.findByProdutoNomeContainingIgnoreCase(nomeProduto));
	}

	@GetMapping("/estoques/produto/{id}")
	public ResponseEntity<EstoqueDTO> recuperarPorIdProduto(@PathVariable int id) {

		Optional<Estoque> consultaEstoqueBD = repositoryEstoque.findByProdutoId(id);

		if (consultaEstoqueBD.isPresent()) {
			Estoque estoqueBD = consultaEstoqueBD.get();
			return ResponseEntity.ok(new EstoqueDTO(estoqueBD));
		}
		return ResponseEntity.notFound().build();

	}

}
