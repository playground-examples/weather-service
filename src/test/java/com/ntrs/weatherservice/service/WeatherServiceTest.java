package com.ntrs.weatherservice.service;

import com.ntrs.weatherservice.domain.Weather;
import com.ntrs.weatherservice.exception.FutureWeatherForeCastNotSupportedException;
import com.ntrs.weatherservice.exception.WeatherNotFoundException;
import com.ntrs.weatherservice.repository.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WeatherServiceTest {

    @Mock
    WeatherRepository weatherRepository;

    @InjectMocks
    private WeatherService weatherService;

    @Test
    public void shouldFindWeatherByZipCodeAndWeatherDate() {
        String zoneId = "Asia/Kolkata";
        String zipCode = "10001-1000";
        LocalDate weatherOn = LocalDate.now();
        Date date = new Date(weatherOn.atStartOfDay(ZoneId.of(zoneId)).toEpochSecond() * 1000);
        when(weatherRepository.findWeatherByZipCodeAndWeatherDate(date, zipCode))
                .thenReturn(Weather.builder().date(date).id(1L).maxTemperature(BigDecimal.valueOf(30.00))
                        .minTemperature(BigDecimal.valueOf(23.00)).build());
        Weather weather = weatherService.findWeatherByZipCodeAndWeatherDate(zipCode, weatherOn, zoneId);
        assertThat(weather).isNotNull();
        assertThat(weather.getId()).isEqualTo(1L);
        assertThat(weather.getDate()).isEqualTo(date);
        assertThat(weather.getMinTemperature()).isEqualTo(BigDecimal.valueOf(23.00));
        assertThat(weather.getMaxTemperature()).isEqualTo(BigDecimal.valueOf(30.00));
    }

    @Test
    public void shouldNotFindWeatherByZipCodeAndWeatherDate() {
        String zoneId = "Asia/Kolkata";
        String zipCode = "10001-1000";
        LocalDate weatherOn = LocalDate.now();
        Date date = new Date(weatherOn.atStartOfDay(ZoneId.of(zoneId)).toEpochSecond() * 1000);
        when(weatherRepository.findWeatherByZipCodeAndWeatherDate(date, zipCode))
                .thenReturn(null);
        WeatherNotFoundException exception = assertThrows(WeatherNotFoundException.class, () -> {
            weatherService.findWeatherByZipCodeAndWeatherDate(zipCode, weatherOn, zoneId);
        });

        String expectedMessage = "Weather for zip code:" + zipCode + " on date:" + weatherOn + " does not exist.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowFutureForeCastNotSupportException() {
        String zoneId = "Asia/Kolkata";
        String zipCode = "10001-1000";
        LocalDate weatherOn = LocalDate.of(2014, Month.JANUARY, 1);
        FutureWeatherForeCastNotSupportedException exception = assertThrows(FutureWeatherForeCastNotSupportedException.class, () -> {
            weatherService.findWeatherByZipCodeAndWeatherDate(zipCode, weatherOn, zoneId);
        });

        String expectedMessage = "Weather forecast for future date:" + weatherOn + " is currently not supported.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
