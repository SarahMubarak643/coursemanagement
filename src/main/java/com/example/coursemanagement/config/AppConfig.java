package com.example.coursemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;


@Configuration
public class AppConfig {

    @Bean
    @Lazy
    public CourseAuditLogger courseAuditLogger() {
        return new CourseAuditLogger();
    }
}
