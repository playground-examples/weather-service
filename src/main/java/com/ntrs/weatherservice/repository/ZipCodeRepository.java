package com.ntrs.weatherservice.repository;

import com.ntrs.weatherservice.domain.ZipCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZipCodeRepository extends JpaRepository<ZipCode, Long> {

    @Query(value = "CALL FIND_ALL_ZIP_CODES_WITH_WEATHER();", nativeQuery = true)
    List<ZipCode> findAllZipCodesWithWeather();

}
