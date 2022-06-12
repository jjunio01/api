package com.github.jjunio01.dto.form.cadastrar;

import javax.validation.constraints.NotNull;

import com.github.jjunio01.dto.UsuarioDTO;
import com.github.jjunio01.model.Cliente;
import com.github.jjunio01.model.Usuario;

/**
 * @author JJunio
 *
 */
public class ClienteDTOFormCadastrar implements DTOFormCadastrar<Cliente> {

	@NotNull
	private String nome;
	@NotNull
	private String codigo;
	@NotNull
	private UsuarioDTO usuario;

	private String telefone;

	public ClienteDTOFormCadastrar(String nome, String codigo, UsuarioDTO usuario, String telefone) {
		this.nome = nome;
		this.codigo = codigo;
		this.usuario = usuario;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public Usuario converterUsuario() {
		return this.usuario.converter();
	}

	@Override
	public Cliente converter() {
		return new Cliente(this.codigo, this.nome, this.telefone);
	}

}
