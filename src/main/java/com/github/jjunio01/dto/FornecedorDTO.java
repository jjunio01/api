package com.github.jjunio01.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.github.jjunio01.model.Fornecedor;

/**
 * @author JJunio
 *
 */

public class FornecedorDTO {

	private int id;
	private String cnpj;
	private String nomeFantasia;
	private String telefone;

	public FornecedorDTO(Fornecedor novoFornecedor) {
		this.id = novoFornecedor.getId();
		this.cnpj = novoFornecedor.getCnpj();
		this.nomeFantasia = novoFornecedor.getNomeFantasia();
		this.telefone = novoFornecedor.getTelefone();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public static List<FornecedorDTO> converter(List<Fornecedor> findAll) {
		return findAll.stream().map(FornecedorDTO::new).collect(Collectors.toList());
	}

}
