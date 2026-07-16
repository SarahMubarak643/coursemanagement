package com.example.coursemanagement.config;

/**
 * Small helper bean used to demonstrate a @Configuration + @Bean +
 * @Lazy setup, as required by the assignment. It logs a line whenever
 * a course is created, updated or deleted.
 */
public class CourseAuditLogger {

    public CourseAuditLogger() {
        System.out.println("CourseAuditLogger bean created (lazily, on first use)");
    }

    public void log(String action, String details) {
        System.out.println("[AUDIT] " + action + " -> " + details);
    }
}
