package com.github.jjunio01.validacao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author JJunio
 *
 */
@RestControllerAdvice
public class ErroDeValidacao {

	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public String handle(MethodArgumentNotValidException exception) {
		String mensagem = exception.getMessage();
		return mensagem;
	}

}
