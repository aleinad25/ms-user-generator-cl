package com.globallogic.demo.app.msusergenerator.common;

public class NotFoundException extends RuntimeException {
	public NotFoundException() {
		super("No se encontro id en el registro de usuarios ");

	}

	public NotFoundException(String id) {
		super("No se encontro id: "+ id +  " en el registro de usuarios ");
	}
}
