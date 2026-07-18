package com.example.coursemanagement.config;


public class CourseAuditLogger {

    public CourseAuditLogger() {
        System.out.println("CourseAuditLogger bean created (lazily, on first use)");
    }

    public void log(String action, String details) {
        System.out.println("[AUDIT] " + action + " -> " + details);
    }
}
