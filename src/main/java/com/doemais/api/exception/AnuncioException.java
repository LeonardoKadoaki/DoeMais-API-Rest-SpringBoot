package com.doemais.api.exception;

public class AnuncioException extends Exception{

	private static final long serialVersionUID = 1L;

	public AnuncioException(String message, Throwable cause) {
		super(message, cause);
	}

	public AnuncioException(String message) {
		super(message);
	}
}
