package com.ntrs.weatherservice.repository;

import com.ntrs.weatherservice.domain.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    @Query(value = "CALL FIND_WEATHER_BY_ZIP_CODE_AND_WEATHER_DATE(:date_in,:zip_code);", nativeQuery = true)
    Weather findWeatherByZipCodeAndWeatherDate(@Param("date_in") Date weatherDate, @Param("zip_code") String zipCode);

}
