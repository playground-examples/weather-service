package com.ntrs.weatherservice.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BusinessException {

	public ResourceNotFoundException(String message, String errorCode) {
		super(message, errorCode);
	}

	public ResourceNotFoundException(String message, String errorCode, HttpStatus httpStatus) {
		super(message, errorCode, httpStatus);
	}
}