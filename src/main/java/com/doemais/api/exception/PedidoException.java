package com.doemais.api.exception;

public class PedidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public PedidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public PedidoException(String message) {
		super(message);
	}
}
