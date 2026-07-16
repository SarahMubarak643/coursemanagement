package com.example.coursemanagement.exception;

/**
 * Thrown when a Course cannot be found by its id.
 */
public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(String message) {
        super(message);
    }
}
