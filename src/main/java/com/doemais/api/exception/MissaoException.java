package com.doemais.api.exception;

public class MissaoException extends Exception{

	private static final long serialVersionUID = 1L;

	public MissaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public MissaoException(String message) {
		super(message);
	}
}
