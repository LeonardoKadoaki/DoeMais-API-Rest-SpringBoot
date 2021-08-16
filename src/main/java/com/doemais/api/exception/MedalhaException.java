package com.doemais.api.exception;

public class MedalhaException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public MedalhaException(String message, Throwable cause) {
		super(message, cause);
	}

	public MedalhaException(String message) {
		super(message);
	}
}
