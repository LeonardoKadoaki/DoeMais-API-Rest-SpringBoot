package com.doemais.api.exception;

public class ConflictException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public ConflictException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConflictException(String message) {
		super(message);
	}

}
