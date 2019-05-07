package ru.springmvc.testproject.exceptions;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Global IOException occured")
	@ExceptionHandler(IOException.class)
	public void handleIOException() {
		logger.error("Global IOException handler executed");
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Global BadfileNameException occured")
	@ExceptionHandler(BadFileNameException.class)
	public void handleBadFileNameException() {
		logger.error("Global BadfileNameException handler executed");
	}
}
