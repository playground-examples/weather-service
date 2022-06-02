package com.ntrs.weatherservice.exception;

public class CheckWeatherException extends SystemException {

	public CheckWeatherException(String message, String errorCode) {
		super(message, errorCode);
	}

}