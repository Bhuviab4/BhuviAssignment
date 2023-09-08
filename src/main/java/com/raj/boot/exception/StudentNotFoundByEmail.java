package com.raj.boot.exception;

public class StudentNotFoundByEmail extends RuntimeException {
	private String message;

	public StudentNotFoundByEmail(String message) {

		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
