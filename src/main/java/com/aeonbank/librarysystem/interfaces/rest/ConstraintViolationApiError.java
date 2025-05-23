package com.aeonbank.librarysystem.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

public class ConstraintViolationApiError extends ApiError {

	private String beanClass;
	private String property;

	public ConstraintViolationApiError(HttpStatus status, String errorCode, ConstraintViolationException ex) {
		super(status, errorCode, ex);
		ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().iterator().next();
		this.beanClass = constraintViolation.getRootBean().getClass().getSimpleName();
		this.property = constraintViolation.getPropertyPath().toString();
		this.message = constraintViolation.getMessage();
	}

	public ConstraintViolationApiError(HttpStatus status, String errorCode, MethodArgumentNotValidException ex) {
		super(status, ex);

		ObjectError objError = ex.getBindingResult().getAllErrors().get(0);
		this.beanClass = objError.getObjectName();
		this.message = objError.getDefaultMessage();
		this.errorCode = errorCode;
		if (objError instanceof FieldError) {
			this.property = ((FieldError) objError).getField();
		}
	}

	public String getBeanClass() {
		return beanClass;
	}

	public String getProperty() {
		return property;
	}
}
