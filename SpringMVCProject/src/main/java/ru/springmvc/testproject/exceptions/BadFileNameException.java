package ru.springmvc.testproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Bad filname")
@SuppressWarnings("serial")
public class BadFileNameException extends Exception {

	public BadFileNameException() {

	}

	public BadFileNameException(String messge) {
		super(messge);
	}

}
