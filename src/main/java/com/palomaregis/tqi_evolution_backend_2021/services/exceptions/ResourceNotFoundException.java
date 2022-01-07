package com.palomaregis.tqi_evolution_backend_2021.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) {
		super("Resource not found. id " + id);
	}
	
}
