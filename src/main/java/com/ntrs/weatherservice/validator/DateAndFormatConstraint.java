package com.ntrs.weatherservice.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {DateAndFormatValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateAndFormatConstraint {
    String message() default "Invalid [${validatedValue}] Date or format of the date.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}