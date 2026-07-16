package com.example.coursemanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

/**
 * Validator used by the @ValidLevel annotation.
 * Checks that the given level String is one of the allowed values.
 */
public class LevelValidator implements ConstraintValidator<ValidLevel, String> {

    private static final List<String> ALLOWED_LEVELS = List.of("BEGINNER", "INTERMEDIATE", "ADVANCED");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return ALLOWED_LEVELS.contains(value.toUpperCase());
    }
}
