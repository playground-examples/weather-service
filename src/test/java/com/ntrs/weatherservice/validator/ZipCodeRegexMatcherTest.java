package com.ntrs.weatherservice.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ZipCodeRegexMatcherTest {

    @InjectMocks
    private ZipCodeRegexMatcher zipCodeRegexMatcher;

    @Test
    public void shouldReturnTrueForValidCase() {
        boolean isValid= zipCodeRegexMatcher.isValid("10000-1000");
        assertThat(isValid).isTrue();
    }

    @Test
    public void shouldReturnFalseForInValidCase() {
        boolean isValid= zipCodeRegexMatcher.isValid("10000-000");
        assertThat(isValid).isFalse();
    }
}