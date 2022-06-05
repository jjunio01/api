package com.github.jjunio01.model;

/**
 * @author JJunio
 *
 */
public enum STATUS {

	EM_APROVACÃO, APROVADO, EM_ENTREGA, ENTREGUE, CANCELADO;

	public static STATUS validar(String novoStatus) {
		try {
			return STATUS.valueOf(novoStatus.toUpperCase());
		} catch (Exception e) {

		}
		return null;
	}

}
