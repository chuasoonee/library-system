package com.aeonbank.librarysystem.exception;

public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 3402005080700100277L;
	protected String errorCode = "generic.error";

	public GenericException(String message) {
		super(message);
	}

	public GenericException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public GenericException(String errorCode, Throwable cause) {
		super(cause.getMessage(), cause);
		this.errorCode = errorCode;
	}

	public GenericException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}
}