package com.github.jjunio01.controller;

import com.github.jjunio01.model.STATUS;

/**
 * @author JJunio
 *
 */
public class PedidoDTOFormCadastrarAtualizar {

	private String status;

	public PedidoDTOFormCadastrarAtualizar(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public STATUS converter(String novoStatus) {
		return STATUS.validar(novoStatus);
	}

}
