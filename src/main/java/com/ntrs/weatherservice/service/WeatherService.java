package com.ntrs.weatherservice.service;

import com.ntrs.weatherservice.constants.ErrorCode;
import com.ntrs.weatherservice.domain.Weather;
import com.ntrs.weatherservice.domain.ZipCode;
import com.ntrs.weatherservice.exception.FutureWeatherForeCastNotSupportedException;
import com.ntrs.weatherservice.exception.ResourceNotFoundException;
import com.ntrs.weatherservice.exception.WeatherNotFoundException;
import com.ntrs.weatherservice.repository.WeatherRepository;
import com.ntrs.weatherservice.repository.ZipCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {

    @Autowired
    ZipCodeRepository zipCodeRepository;
    @Autowired
    WeatherRepository weatherRepository;

    public Weather findWeatherByZipCodeAndWeatherDate(final String zipCode, final LocalDate weatherOn, final String zoneId) {
        performFutureWeatherForecastValidation(weatherOn, zoneId);
        Date date = new Date(weatherOn.atStartOfDay(ZoneId.of(zoneId)).toEpochSecond() * 1000);
        return Optional.ofNullable(weatherRepository.findWeatherByZipCodeAndWeatherDate(date, zipCode)).orElseThrow(
                () -> new WeatherNotFoundException("Weather for zip code:" + zipCode + " on date:" + weatherOn + " does not exist.", ErrorCode.WEATHER_NOT_FOUND, HttpStatus.NOT_FOUND)
        );
    }

    private void performFutureWeatherForecastValidation(LocalDate weatherOn, final String zoneId) {
        if (weatherOn.isBefore(LocalDate.now(ZoneId.of(zoneId)))) {
            throw new FutureWeatherForeCastNotSupportedException("Weather forecast for future date:" + weatherOn + " is currently not supported.", ErrorCode.FUTURE_WEATHER_FORECAST_IS_NOT_SUPPORTED, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    public List<ZipCode> getAllInformation() {
        return Optional.ofNullable(zipCodeRepository.findAllZipCodesWithWeather()).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found.", ErrorCode.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND)
        );
    }

}
