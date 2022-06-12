package com.github.jjunio01.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author JJunio
 *
 */
@Entity(name = "FORNECEDOR")
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = -5270992910804682425L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_fornecedor")
	private int id;

	@Column(name = "nome_fantasia")
	private String nomeFantasia;

	@Column(name = "razao_social")
	private String razaoSocial;

	@OneToMany(mappedBy = "fornecedor")
	private List<Endereco> enderecos = new ArrayList<Endereco>();

	@OneToMany(mappedBy = "fornecedor")
	private List<Estoque> estoques = new ArrayList<Estoque>();

	private String cnpj;
	private String telefone;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "usuario_id_usuario")
	private Usuario usuario;

	public Fornecedor() {

	}

	public Fornecedor(String nomeFantasia, String razaoSocial, String cnpj, String telefone) {
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.telefone = telefone;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public List<Estoque> getEstoques() {
		return estoques;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void adicionarEstoque(Estoque novoEstoque) {
		this.estoques.add(novoEstoque);
		novoEstoque.setFornecedor(this);
	}

	public void adicionarEndereco(Endereco novoEndereco) {
		this.enderecos.add(novoEndereco);
		novoEndereco.setFornecedor(this);
	}

	public void removerEstoque(Estoque estoque) {
		this.estoques.remove(estoque);
		estoque.setFornecedor(null);
	}

	public void removerEndereco(Endereco endereco) {
		this.enderecos.remove(endereco);
		endereco.setFornecedor(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + id;
		result = prime * result + ((nomeFantasia == null) ? 0 : nomeFantasia.hashCode());
		result = prime * result + ((razaoSocial == null) ? 0 : razaoSocial.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (id != other.id)
			return false;
		if (nomeFantasia == null) {
			if (other.nomeFantasia != null)
				return false;
		} else if (!nomeFantasia.equals(other.nomeFantasia))
			return false;
		if (razaoSocial == null) {
			if (other.razaoSocial != null)
				return false;
		} else if (!razaoSocial.equals(other.razaoSocial))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

}
