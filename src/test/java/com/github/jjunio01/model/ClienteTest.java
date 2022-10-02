/**
 * 
 */
package com.github.jjunio01.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

/**
 * @author jjuni
 *
 */
class ClienteTest {

	@Test
	void novoClienteTest() {
		Cliente novo = new Cliente("123456", "Cliente", "9999-8888");
		assertEquals("123456", novo.getCodigo());
	}

	@Test
	void setCodigoClienteTest() {
		Cliente novo = new Cliente("123456", "Cliente", "9999-8888");
		novo.setCodigo("333");
		assertEquals("333", novo.getCodigo());
	}

	@Test
	void setNomeClienteTest() {
		Cliente novo = new Cliente("123456", "Cliente", "9999-8888");
		novo.setNome("Novo");
		assertEquals("Novo", novo.getNome());
	}

	@Test
	void setTelefoneClienteTest() {
		Cliente novo = new Cliente("123456", "Cliente", "9999-8888");
		novo.setTelefone("1111-2222");
		assertEquals("1111-2222", novo.getTelefone());
	}

	@Test
	void adicionaEnderecoTest() {
		Cliente novo = new Cliente();
		Endereco endereco = new Endereco("São João", "Bairro", "Rua", "123", "Perto da minha casa");
		novo.adicionarEndereco(endereco);
		assertFalse(novo.getEnderecos().isEmpty());
	}
}
