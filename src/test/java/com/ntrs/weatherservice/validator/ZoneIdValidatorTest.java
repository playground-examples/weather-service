package com.ntrs.weatherservice.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ZoneIdValidatorTest {

    @InjectMocks
    private ZoneIdValidator zoneIdValidator;

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @Test
    public void shouldReturnTrueForValidCase() {
        String zoneId = "Asia/Kolkata";
        boolean isValid= zoneIdValidator.isValid(zoneId,constraintValidatorContext);
        assertThat(isValid).isTrue();
    }

    @Test
    public void shouldReturnFalseForInValidCase() {
        String zoneId = "Fake/Zone";
        boolean isValid= zoneIdValidator.isValid(zoneId,constraintValidatorContext);
        assertThat(isValid).isFalse();
    }
}