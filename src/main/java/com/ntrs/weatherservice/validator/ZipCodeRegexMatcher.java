package com.ntrs.weatherservice.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ZipCodeRegexMatcher {
    public static final String ZIP_REGEX = "^[0-9]{5}(?:-[0-9]{4})?$";
    private final Pattern pattern;

    public ZipCodeRegexMatcher() {
        pattern = Pattern.compile(ZIP_REGEX);
    }

    public boolean isValid(String zipCode) {
        return pattern.matcher(zipCode).matches();
    }
}
