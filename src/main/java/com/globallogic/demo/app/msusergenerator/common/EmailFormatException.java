package com.globallogic.demo.app.msusergenerator.common;

public class EmailFormatException extends RuntimeException {
	public EmailFormatException() {
		super("Correo no valido, favor ingresar nuevamente.");
	}

	public EmailFormatException(String message) {
		super("Invalido "+ message);
	}
}
