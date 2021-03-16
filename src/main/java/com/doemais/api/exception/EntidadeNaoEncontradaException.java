package com.doemais.api.exception;

public class EntidadeNaoEncontradaException extends Exception{

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntidadeNaoEncontradaException(String message) {
		super(message);
	}
}
