package com.doctortrain.exception;

public class ApplicationException extends RuntimeException {
	public ApplicationException() {
	}
	public ApplicationException(String msg) {
		super(msg);
	}
}
