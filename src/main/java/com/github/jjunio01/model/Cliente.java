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
@Entity(name = "CLIENTE")
public class Cliente implements Serializable {

	private static final long serialVersionUID = -4592546754689915905L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private int id;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Endereco> enderecos;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Pedido> pedidos;

	private String codigo;
	private String telefone;
	private String nome;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "usuario_id_usuario")
	private Usuario usuario;

	public Cliente() {
		this.enderecos = new ArrayList<Endereco>();
		this.pedidos = new ArrayList<Pedido>();
	}

	public Cliente(String codigo, String nome, Usuario usuario) {
		this.codigo = codigo;
		this.nome = nome;
		this.usuario = usuario;
	}

	public Cliente(String codigo, String nome, Usuario usuario, String telefone) {
		this.codigo = codigo;
		this.telefone = telefone;
		this.nome = nome;
		this.usuario = usuario;
	}

	public Cliente(String codigo, String nome, String telefone) {
		this.codigo = codigo;
		this.nome = nome;
		this.telefone = telefone;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void adicionarEndereco(Endereco novoEndereco) {
		this.enderecos.add(novoEndereco);
		novoEndereco.setCliente(this);
	}

	public void adicionarPedido(Pedido novoPedido) {
		this.pedidos.add(novoPedido);
		novoPedido.setCliente(this);
	}

	public void removeEndereco(Endereco novoEndereco) {
		this.enderecos.remove(novoEndereco);
		novoEndereco.setCliente(null);
	}

	public void removePedido(Pedido novoPedido) {
		this.pedidos.remove(novoPedido);
		novoPedido.setCliente(null);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Cliente other = (Cliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

}
