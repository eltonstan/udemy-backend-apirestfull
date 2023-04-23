package com.teste.udemybackendapirestfull.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 447607153198312753L;

	public ResourceNotFoundException(String mensagem) {
		super(mensagem);
	}
	
}
