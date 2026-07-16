package com.example.coursemanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Basic smoke test - verifies the Spring application context loads successfully.
 * Requires a running MySQL instance matching application.properties.
 */
@SpringBootTest
class CoursemanagementApplicationTests {

    @Test
    void contextLoads() {
        // If the application context fails to start, this test will fail.
    }
}
