package com.example.coursemanagement.service;

import com.example.coursemanagement.config.CourseAuditLogger;
import com.example.coursemanagement.entity.Course;
import com.example.coursemanagement.exception.CourseNotFoundException;
import com.example.coursemanagement.repository.CourseRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer - all business logic for Course lives here.
 * Controllers must never talk to the repository directly.
 */
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final NotificationService notificationService;
    private final CourseAuditLogger auditLogger;

    // Constructor injection.
    // Because EmailNotificationService is @Primary, it is the one injected here.
    // auditLogger comes from the @Lazy @Bean declared in AppConfig - it is only
    // actually instantiated the first time a Course is created/updated/deleted.
    public CourseService(CourseRepository courseRepository, NotificationService notificationService,
                          CourseAuditLogger auditLogger) {
        this.courseRepository = courseRepository;
        this.notificationService = notificationService;
        this.auditLogger = auditLogger;
    }

    public List<Course> getAllCourses(String sort) {
        if (sort != null && !sort.isBlank()) {
            String[] parts = sort.split(",");
            String property = parts[0];
            Sort.Direction direction = (parts.length > 1 && parts[1].equalsIgnoreCase("desc"))
                    ? Sort.Direction.DESC
                    : Sort.Direction.ASC;
            return courseRepository.findAll(Sort.by(direction, property));
        }
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));
    }

    public List<Course> getCoursesByCategory(String category) {
        return courseRepository.findByCategory(category);
    }

    public Course createCourse(Course course) {
        Course saved = courseRepository.save(course);
        notificationService.sendNotification("New course created: " + saved.getName());
        auditLogger.log("CREATE", "course id=" + saved.getId());
        return saved;
    }

    public Course updateCourse(Long id, Course updatedCourse) {
        Course existing = getCourseById(id);
        existing.setName(updatedCourse.getName());
        existing.setCategory(updatedCourse.getCategory());
        existing.setPrice(updatedCourse.getPrice());
        existing.setDurationHours(updatedCourse.getDurationHours());
        existing.setLevel(updatedCourse.getLevel());
        existing.setActive(updatedCourse.getActive());
        existing.setCode(updatedCourse.getCode());
        Course saved = courseRepository.save(existing);
        notificationService.sendNotification("Course updated: " + saved.getName());
        auditLogger.log("UPDATE", "course id=" + saved.getId());
        return saved;
    }

    public Course patchCourse(Long id, Course partial) {
        Course existing = getCourseById(id);
        if (partial.getName() != null) existing.setName(partial.getName());
        if (partial.getCategory() != null) existing.setCategory(partial.getCategory());
        if (partial.getPrice() != null) existing.setPrice(partial.getPrice());
        if (partial.getDurationHours() != null) existing.setDurationHours(partial.getDurationHours());
        if (partial.getLevel() != null) existing.setLevel(partial.getLevel());
        if (partial.getActive() != null) existing.setActive(partial.getActive());
        if (partial.getCode() != null) existing.setCode(partial.getCode());
        Course saved = courseRepository.save(existing);
        notificationService.sendNotification("Course patched: " + saved.getName());
        auditLogger.log("PATCH", "course id=" + saved.getId());
        return saved;
    }

    public void deleteCourse(Long id) {
        Course existing = getCourseById(id);
        courseRepository.delete(existing);
        notificationService.sendNotification("Course deleted: " + existing.getName());
        auditLogger.log("DELETE", "course id=" + id);
    }
}
