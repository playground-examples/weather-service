package com.ntrs.weatherservice.exception;

import org.springframework.http.HttpStatus;

public class FutureWeatherForeCastNotSupportedException extends BusinessException {

	public FutureWeatherForeCastNotSupportedException(String message, String errorCode) {
		super(message, errorCode);
	}

	public FutureWeatherForeCastNotSupportedException(String message, String errorCode, HttpStatus httpStatus) {
		super(message, errorCode, httpStatus);
	}
}