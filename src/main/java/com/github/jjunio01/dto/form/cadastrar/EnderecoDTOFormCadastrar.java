package com.github.jjunio01.dto.form.cadastrar;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.github.jjunio01.model.Cliente;
import com.github.jjunio01.model.Endereco;
import com.github.jjunio01.repository.ClienteRepository;
import com.github.jjunio01.repository.EnderecoRepository;

/**
 * @author JJunio
 *
 */
public class EnderecoDTOFormCadastrar implements DTOFormCadastrar<Endereco> {

	@NotNull
	private String cidade;

	@NotNull
	private String bairro;

	@NotNull
	private String rua;

	@NotNull
	private String numero;

	private String referencia;

	public EnderecoDTOFormCadastrar(String cidade, String bairro, String rua, String numero, String referencia) {
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
		this.referencia = referencia;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	@Override
	public Endereco converter() {
		return new Endereco(this.cidade, this.bairro, this.rua, this.numero, this.referencia);
	}

	public Cliente incluirEndereco(ClienteRepository repositoryCliente, int id, EnderecoRepository repositoryEndereco) {
		Cliente cliente = null;
		Optional<Cliente> consultaCliente = repositoryCliente.findById(id);
		if (consultaCliente.isPresent()) {
			Cliente clienteBD = consultaCliente.get();
			Endereco enderecoBD = repositoryEndereco.save(this.converter());
			clienteBD.adicionarEndereco(enderecoBD);
			return clienteBD;
		}
		return cliente;
	}

}
