package com.ntrs.weatherservice.exception;

import org.springframework.http.HttpStatus;

public class WeatherNotFoundException extends BusinessException {

	public WeatherNotFoundException(String message, String errorCode) {
		super(message, errorCode);
	}
	
	public WeatherNotFoundException(String message, String errorCode, HttpStatus httpStatus) {
		super(message, errorCode, httpStatus);
	}
}