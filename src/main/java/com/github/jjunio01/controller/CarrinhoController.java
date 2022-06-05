package com.github.jjunio01.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.github.jjunio01.dto.form.cadastrar.ItemProdutoDTOForm;
import com.github.jjunio01.model.Carrinho;
import com.github.jjunio01.model.Estoque;
import com.github.jjunio01.repository.CarrinhoRepository;
import com.github.jjunio01.repository.EstoqueRepository;

/**
 * @author JJunio
 *
 */
@Controller
public class CarrinhoController {

	@Autowired
	private CarrinhoRepository repositoryCarrinho;

	@Autowired
	private EstoqueRepository repositoryEstoque;

	public Carrinho salvar(List<ItemProdutoDTOForm> formItemProduto) {

		Carrinho novoCarrinho = new Carrinho();

		for (ItemProdutoDTOForm itemProdutoDTOForm : formItemProduto) {
			Optional<Estoque> consultaProdutoBD = repositoryEstoque.findByProdutoId(itemProdutoDTOForm.getIdProduto());
			if (!consultaProdutoBD.isPresent()) {
				return null;
			}
			Estoque estoqueBD = consultaProdutoBD.get();
			if (estoqueBD.getQuantidade() < itemProdutoDTOForm.getQuantidade()) {
				return null;
			}
			novoCarrinho.adicionarItem((itemProdutoDTOForm.converter(estoqueBD.getProduto())));
			estoqueBD.retirarEstoque(itemProdutoDTOForm.getQuantidade());
		}
		novoCarrinho.atualizaValorTotal();

		return repositoryCarrinho.save(novoCarrinho);

	}

}
