package com.github.jjunio01.controller;

import com.github.jjunio01.model.Produto;
import com.github.jjunio01.repository.CarrinhoRepository;
import com.github.jjunio01.repository.EstoqueRepository;

/**
 * @author JJunio
 *
 */
public class VerificaProdutoEstoque {
	
	
	static Produto estaDisponivel(CarrinhoRepository repositpryCarrinho, EstoqueRepository repositoryEstoque, int idProduto) {
		if (existeProduto(repositpryCarrinho, idProduto)) {
			
		}
		return null;
	}
	
	static private boolean existeProduto(CarrinhoRepository repositpryCarrinho, int idProduto) {
		return repositpryCarrinho.existsById(idProduto);
	}

}
