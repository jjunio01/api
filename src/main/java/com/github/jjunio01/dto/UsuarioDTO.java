package com.github.jjunio01.dto;

import com.github.jjunio01.dto.form.cadastrar.DTOFormCadastrar;
import com.github.jjunio01.model.Usuario;

/**
 * @author JJunio
 *
 */
public class UsuarioDTO implements DTOFormCadastrar<Usuario> {

	private String senha;
	private String email;

	public UsuarioDTO() {

	}

	public String getSenha() {
		return senha;
	}

	public String getEmail() {
		return email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public Usuario converter() {
		return new Usuario(this.email, this.senha);
	}

}
