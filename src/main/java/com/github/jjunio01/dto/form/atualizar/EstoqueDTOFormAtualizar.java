package com.github.jjunio01.dto.form.atualizar;

import com.github.jjunio01.model.Estoque;

/**
 * @author JJunio
 *
 */
public class EstoqueDTOFormAtualizar {

	private ProdutoDTOFormAtualizar produto;
	private int quantidade;

	public ProdutoDTOFormAtualizar getProduto() {
		return produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Estoque atualizarQuantidade(Estoque estoqueBD) {
		estoqueBD.setQuantidade(this.quantidade);
		return estoqueBD;
	}

	
	

}
