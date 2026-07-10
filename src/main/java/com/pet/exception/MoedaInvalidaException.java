package com.pet.exception;

public class MoedaInvalidaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	

	public MoedaInvalidaException(String message) {
		super(message);
	}

}
