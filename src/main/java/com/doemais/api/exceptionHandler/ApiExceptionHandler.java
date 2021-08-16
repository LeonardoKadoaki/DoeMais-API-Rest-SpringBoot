package com.doemais.api.exceptionHandler;

import java.util.Date;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.doemais.api.exception.ConflictException;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.exception.MissaoException;
import com.doemais.api.exception.MoedasException;
import com.doemais.api.exception.ObjectError;


@ControllerAdvice
public class ApiExceptionHandler{
	
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<?> handleConflictException(ConflictException exception, WebRequest request){
		
		ObjectError error = new ObjectError(HttpStatus.CONFLICT.value(), exception.getMessage(), new Date());
		
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException exception, WebRequest request){
		
		ObjectError error = new ObjectError(HttpStatus.NOT_FOUND.value(), exception.getMessage(), new Date());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception, WebRequest request){
		
		ObjectError error = new ObjectError(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), new Date());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	public ResponseEntity<?> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException exception, WebRequest request){
		
		ObjectError error = new ObjectError(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), new Date());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleNullPointerException(NullPointerException exception, WebRequest request){
		
		ObjectError error = new ObjectError(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), new Date());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeException(RuntimeException exception, WebRequest request){
		
		ObjectError error = new ObjectError(HttpStatus.CONFLICT.value(), exception.getMessage(), new Date());
		
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(MoedasException.class)
	public ResponseEntity<?> handleMoedasException(MoedasException exception, WebRequest request){
		
		ObjectError error = new ObjectError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), new Date());
		
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MissaoException.class)
	public ResponseEntity<?> handleMissaoException(MissaoException exception, WebRequest request){
		
		ObjectError error = new ObjectError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), new Date());
		
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}