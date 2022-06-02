package com.ntrs.weatherservice.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ntrs.weatherservice.domain.Weather;
import com.ntrs.weatherservice.domain.ZipCode;
import com.ntrs.weatherservice.exception.ResourceNotFoundException;
import com.ntrs.weatherservice.exception.WeatherNotFoundException;
import com.ntrs.weatherservice.service.WeatherService;
import com.ntrs.weatherservice.validator.ZipCodeConstraint;
import com.ntrs.weatherservice.validator.ZoneIdConstraint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/v1/weather")
@Validated
@Tag(name = "Weather", description = "Weather Checking Controller.")
public class WeatherController {

    @Autowired
    public WeatherService weatherService;

    @Operation(summary = "Get Weather Information based on Zip Code and Date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Found - Weather Information for Given Zip Code and Date"),
            @ApiResponse(responseCode = "404", description = "Not found - Weather Information not found")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findWeatherByZipCodeAndWeatherDate(@Parameter(required = true, name = "zipCode", example = "10001-0020", description = "Area Code (5 Char)-City Code(4 Char)")
                                                                     @NonNull @ZipCodeConstraint @RequestParam(name = "zipCode") String zipCode,
                                                                     @Parameter(required = true, name = "weatherOn", example = "2000-10-31", description = "Weather Date Format should be yyyy-MM-dd")
                                                                     @NonNull @RequestParam(name = "weatherOn") @JsonFormat(pattern = "yyyy-MM-dd")
                                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate weatherOn,
                                                                     @Parameter(example = "Asia/Kolkata", description = "Specify the zoneId")
                                                                     @ZoneIdConstraint @RequestParam(required = false, value = "Asia/Kolkata") String zoneId) {
        log.info("Get Weather by zipCode {}", zipCode);
        try {
            Weather weather = this.weatherService.findWeatherByZipCodeAndWeatherDate(zipCode, weatherOn, zoneId);
            return new ResponseEntity<>(weather, HttpStatus.FOUND);
        } catch (WeatherNotFoundException daie) {
            return new ResponseEntity<>(daie.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get all Zip codes with Weather Information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Found - All Zip codes with weather information"),
            @ApiResponse(responseCode = "404", description = "Not found - Any information.")
    })

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/all")
    public ResponseEntity<Object> getAllZipCodeWithCorrespondingWeatherInformation() {
        log.info("Retrieving  all information");
        try {
            List<ZipCode> zipCode = this.weatherService.getAllInformation();
            return new ResponseEntity<>(zipCode, HttpStatus.FOUND);
        } catch (ResourceNotFoundException daie) {
            return new ResponseEntity<>(daie.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
