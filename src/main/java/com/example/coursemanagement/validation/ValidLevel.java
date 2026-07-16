package com.example.coursemanagement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation annotation.
 * Ensures the "level" field of a Course is one of the allowed values:
 * BEGINNER, INTERMEDIATE, ADVANCED.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LevelValidator.class)
public @interface ValidLevel {

    String message() default "level must be one of BEGINNER, INTERMEDIATE, ADVANCED";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
