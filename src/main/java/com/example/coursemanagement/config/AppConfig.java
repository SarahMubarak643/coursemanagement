package com.example.coursemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Single @Configuration class for this project.
 * Declares one @Bean, marked @Lazy so it is only created the first
 * time it is actually requested rather than at application startup.
 */
@Configuration
public class AppConfig {

    @Bean
    @Lazy
    public CourseAuditLogger courseAuditLogger() {
        return new CourseAuditLogger();
    }
}
