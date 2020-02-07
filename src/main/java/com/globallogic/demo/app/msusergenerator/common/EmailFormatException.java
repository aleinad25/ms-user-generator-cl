package com.globallogic.demo.app.msusergenerator.common;

public class EmailFormatException extends RuntimeException {
	public EmailFormatException() {
		super("Invalid email please enter data again");
	}

	public EmailFormatException(String message) {
		super("Invalid "+ message);
	}
}
