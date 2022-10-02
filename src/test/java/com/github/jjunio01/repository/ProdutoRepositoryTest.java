package com.github.jjunio01.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.github.jjunio01.model.Produto;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("teste")
public class ProdutoRepositoryTest {
	
	@Autowired
	private ProdutoRepository repository;

	@Test
	@Transactional(rollbackOn = Exception.class)
	public void salvarProduto() {		
		Produto novoProduto = new Produto("Café", 6.78, "Ouro Verde", "Quente");
		Produto produtoBD = repository.save(novoProduto);
		assertNotNull(produtoBD.getId());
	}
	
	@Test
	@Transactional(rollbackOn = Exception.class)
	public void atualizarProduto() {		
		Produto novoProduto = new Produto("Café", 6.78, "Ouro Verde", "Quente");
		Produto produtoBD = repository.save(novoProduto);
		produtoBD.setNome("Açucar");
		repository.flush();
		Optional<Produto> produtoConsulta = repository.findById(produtoBD.getId());
		assertEquals( "Açucar",produtoConsulta.get().getNome());
	}
	
	@Test
	public void removerProduto() {		
		Produto novoProduto = new Produto("Café", 6.78, "Ouro Verde", "Quente");
		Produto produtoBD = repository.save(novoProduto);
		assertNotNull(produtoBD.getId());
		repository.deleteById(produtoBD.getId());
		Optional<Produto> produtoConsulta = repository.findById(novoProduto.getId());
		assertFalse(produtoConsulta.isPresent());
	}
	
	@Test
	public void recuperarListaVazia() {
		assertEquals(0,repository.findAll().size());
	}
	
	@Test
	@Transactional(rollbackOn = Exception.class)
	public void recuperarListaComProdutos() {		
		Produto novoProduto = new Produto("Café", 6.78, "Ouro Verde", "Quente");
		repository.save(novoProduto);
		assertEquals(1,repository.findAll().size());
	}
	
	@Test
	@Transactional(rollbackOn = Exception.class)
	public void recuperarPorID() {		
		Produto novoProduto = new Produto("Café", 6.78, "Ouro Verde", "Quente");
		Produto produtoBD = repository.save(novoProduto);
		Optional<Produto> produtoConsulta = repository.findById(novoProduto.getId());
		assertEquals(produtoBD,produtoConsulta.get());
		
	}

}
