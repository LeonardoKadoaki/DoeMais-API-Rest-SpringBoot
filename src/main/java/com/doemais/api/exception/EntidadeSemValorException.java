package com.doemais.api.exception;

public class EntidadeSemValorException extends Exception{

	private static final long serialVersionUID = 1L;

	public EntidadeSemValorException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntidadeSemValorException(String message) {
		super(message);
	}
}
