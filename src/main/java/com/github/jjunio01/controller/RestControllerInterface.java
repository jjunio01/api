package com.github.jjunio01.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author JJunio
 *
 */
public interface RestControllerInterface<R, C, A> {

	public List<R> listarTodos();

	public ResponseEntity<R> inserir(@RequestBody @Valid C formG, UriComponentsBuilder uriBuilder);

	public ResponseEntity<R> recuperarPorId(@PathVariable int id);

	public ResponseEntity<R> atualizar(@PathVariable int id, @RequestBody @Valid A formG);

	public ResponseEntity<R> remover(@PathVariable int id);

}
