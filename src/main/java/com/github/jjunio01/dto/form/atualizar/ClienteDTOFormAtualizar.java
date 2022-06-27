package com.github.jjunio01.dto.form.atualizar;

import com.github.jjunio01.dto.UsuarioDTO;
import com.github.jjunio01.model.Cliente;

/**
 * @author JJunio
 *
 */
public class ClienteDTOFormAtualizar {

	private String nome;
	private String codigo;
	private String telefone;
	private UsuarioDTO usuario;

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

	public Cliente atualizar(Cliente clienteBD) {

		clienteBD.setNome(this.nome);
		clienteBD.setCodigo(this.codigo);
		clienteBD.setTelefone(this.telefone);

		return clienteBD;
	}

}
