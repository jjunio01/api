package com.github.jjunio01.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.jjunio01.model.Usuario;
import com.github.jjunio01.repository.UsuarioRepository;

/**
 * @author JJunio
 *
 */
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repositoryUsuario;
	
	public Usuario inserir(Usuario usuario) {

		return repositoryUsuario.save(usuario);

	}

}
