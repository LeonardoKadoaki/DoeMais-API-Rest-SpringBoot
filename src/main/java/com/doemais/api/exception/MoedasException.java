package com.doemais.api.exception;

public class MoedasException extends Exception{

	private static final long serialVersionUID = 1L;

	public MoedasException(String message, Throwable cause) {
		super(message, cause);
	}

	public MoedasException(String message) {
		super(message);
	}
}
