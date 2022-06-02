package com.ntrs.weatherservice.constants;

public class ErrorCode {
	/**
	 * Error Code for any internal system error
	 */
	public static final String SYSTEM_ERROR = "ERR_SYS_001";
	
	/**
	 * Error Code for gateway timeout error
	 */
	public static final String TIMEOUT_ERROR = "ERR_SYS_002";

	/**
	 * Error Code for general client error
	 */
	public static final String CLIENT_ERROR = "ERR_CLIENT_000";


	/**
	 * Error Code for Future weather forecast not supported
	 */
	public static final String FUTURE_WEATHER_FORECAST_IS_NOT_SUPPORTED = "ERR_CLIENT_001";
	
	/**
	 * Error Code for any error related to weather
	 */
	public static final String WEATHER_NOT_FOUND = "ERR_CLIENT_002";

	public static final String RESOURCE_NOT_FOUND = "ERR_CLIENT_003";

}