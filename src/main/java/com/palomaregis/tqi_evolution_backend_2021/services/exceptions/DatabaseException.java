package com.palomaregis.tqi_evolution_backend_2021.services.exceptions;

public class DatabaseException extends RuntimeException{

	private static final long serialVersionUID = 1L;
		
	public DatabaseException(String msg) {
		super(msg);
	}
}
