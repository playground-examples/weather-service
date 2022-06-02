package com.ntrs.weatherservice.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {ZoneIdValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ZoneIdConstraint {
    String message() default "Invalid [${validatedValue}] ZoneId value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}