package com.ntrs.weatherservice.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class GregorianDateMatcherTest {
    @InjectMocks
    private GregorianDateMatcher gregorianDateMatcher;

    @Test
    public void shouldReturnTrueForValidCase() {
        String date = "2022-10-10";
        boolean isValid = gregorianDateMatcher.matches(date);
        assertThat(isValid).isTrue();
    }

    @Test
    public void shouldReturnFalseForInValidCase() {
        String date = "2022-10-1110";
        boolean isValid = gregorianDateMatcher.matches(date);
        assertThat(isValid).isFalse();
    }

}