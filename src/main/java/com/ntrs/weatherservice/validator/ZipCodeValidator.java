package com.ntrs.weatherservice.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ZipCodeValidator implements ConstraintValidator<ZipCodeConstraint, String> {

    @Autowired
    private ZipCodeRegexMatcher zipCodeRegexMatcher;

    @Override
    public boolean isValid(String zipCode, ConstraintValidatorContext constraintValidatorContext) {
        return zipCodeRegexMatcher.isValid(zipCode);
    }
}