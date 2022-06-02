package com.ntrs.weatherservice.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.zone.ZoneRulesException;
import java.util.Objects;

@Component
@Slf4j
public class ZoneIdValidator implements ConstraintValidator<ZoneIdConstraint, String> {

    @Override
    public boolean isValid(String zoneId, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if(Objects.nonNull(zoneId))
                ZoneId.of(zoneId);
            else
                return false;
        } catch (ZoneRulesException zoneIdException) {
            log.error("InCorrect ZonId information provided."+zoneIdException.getMessage());
            return false;
        } catch (DateTimeException zoneIdException) {
            log.error("InCorrect Zone Id information provided."+zoneIdException.getMessage());
            return false;
        }
        return true;
    }
}