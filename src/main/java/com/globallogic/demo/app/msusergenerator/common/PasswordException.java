package com.globallogic.demo.app.msusergenerator.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PasswordException extends RuntimeException {
	public PasswordException() {
		super("Invalid Password ");

	}

	public PasswordException(String problem) {
		super("Invalid: " + problem );
	}
}