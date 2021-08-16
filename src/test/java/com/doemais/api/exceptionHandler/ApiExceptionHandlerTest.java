package com.doemais.api.exceptionHandler;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import javax.mail.Message;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.context.request.WebRequest;

import com.doemais.api.exception.ConflictException;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.exception.MissaoException;
import com.doemais.api.exception.MoedasException;
import com.doemais.api.exception.ObjectError;

@SpringBootTest
class ApiExceptionHandlerTest {

	private ApiExceptionHandler api = new ApiExceptionHandler();
	private ConflictException excep = new ConflictException("error");
	private WebRequest req;
	private EntidadeNaoEncontradaException entity = new EntidadeNaoEncontradaException("error");
    private HttpMessageNotReadableException message = new HttpMessageNotReadableException("error");
    private InvalidDataAccessApiUsageException invalid = new InvalidDataAccessApiUsageException("error");
    private NullPointerException pointer = new NullPointerException();
    private RuntimeException run = new RuntimeException();
    private MoedasException moeda = new MoedasException("error");
    private MissaoException missao = new MissaoException("error");
	
	@Test
	void TestHandleConflictException() {
		ObjectError error = new ObjectError(HttpStatus.CONFLICT.value(), excep.getMessage(), new Date());
		new ResponseEntity<>(error, HttpStatus.CONFLICT);
		api.handleConflictException(excep, req);
		assertNotNull(error);

	}

	@Test
	void TestHandleEntidadeEncontradaException() {
		ObjectError err = new ObjectError(HttpStatus.NOT_FOUND.value(), excep.getMessage(), new Date());
		new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
		api.handleEntidadeNaoEncontradaException(entity, req);
		assertNotNull(err);
	}

	@Test
	void TestHandleHttpMessageNotReadableException() {
		ObjectError error = new ObjectError(HttpStatus.BAD_REQUEST.value(), excep.getMessage(), new Date());
        new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        api.handleHttpMessageNotReadableException(message, req);
        assertNotNull(error);
	}
	
	@Test
	void TestHandleInvalidDataAccessApiUsageException() {
		ObjectError error = new ObjectError(HttpStatus.BAD_REQUEST.value(), excep.getMessage(), new Date());
		new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		api.handleInvalidDataAccessApiUsageException(invalid, req);
		assertNotNull(error);
	}
	
	@Test
	void TestHandleNullPointerException() {
        ObjectError error = new ObjectError(HttpStatus.BAD_REQUEST.value(), excep.getMessage(), new Date());
		new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		api.handleNullPointerException(pointer, req);
		assertNotNull(error);
	}
	
	@Test
	void TestHandleRuntimeException() {
        ObjectError error = new ObjectError(HttpStatus.CONFLICT.value(), excep.getMessage(), new Date());
		new ResponseEntity<>(error, HttpStatus.CONFLICT);
		api.handleRuntimeException(run, req);
		assertNotNull(error);
	}
	
	@Test
	void TestHandleMoedasException() {
        ObjectError error = new ObjectError(HttpStatus.INTERNAL_SERVER_ERROR.value(), excep.getMessage(), new Date());
		new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		api.handleMoedasException(moeda, req);
		assertNotNull(error);
	}
	
	@Test
	void TestHandleMissaoException() {
        ObjectError error = new ObjectError(HttpStatus.INTERNAL_SERVER_ERROR.value(), excep.getMessage(), new Date());
	    new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    api.handleMissaoException(missao, req);
	    assertNotNull(error);
		
	}
	
}
