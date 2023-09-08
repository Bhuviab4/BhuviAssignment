package com.raj.boot.exception;

public class StudentNotFoundByIdException extends RuntimeException {

	private String message;

	public StudentNotFoundByIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
