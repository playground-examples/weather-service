package com.ntrs.weatherservice.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

@Component
public class DateAndFormatValidator implements ConstraintValidator<DateAndFormatConstraint, String> {

    @Autowired
    private GregorianDateMatcher gregorianDateMatcher;

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        return gregorianDateMatcher.matches(date);
    }
}