package com.ntrs.weatherservice.exception;

import org.springframework.http.HttpStatus;

public class CustomSQLException extends BusinessException {

	public CustomSQLException(String message, String errorCode) {
		super(message, errorCode);
	}

	public CustomSQLException(String message, String errorCode, HttpStatus httpStatus) {
		super(message, errorCode, httpStatus);
	}
}