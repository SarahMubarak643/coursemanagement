package com.example.coursemanagement.controller;

import com.example.coursemanagement.entity.Course;
import com.example.coursemanagement.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/courses")
@Tag(name = "Courses", description = "Course Management REST API")
public class CourseRestController {

    private final CourseService courseService;

    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(summary = "Get all courses, optionally sorted, e.g. ?sort=price,asc")
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(@RequestParam(required = false) String sort) {
        return ResponseEntity.ok(courseService.getAllCourses(sort));
    }

    @Operation(summary = "Get a course by its id")
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @Operation(summary = "Get all courses in a given category")
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Course>> getCoursesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(courseService.getCoursesByCategory(category));
    }

    @Operation(summary = "Create a new course")
    @PostMapping
    public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) {
        Course created = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Fully update an existing course")
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @Valid @RequestBody Course course) {
        return ResponseEntity.ok(courseService.updateCourse(id, course));
    }

    @Operation(summary = "Partially update an existing course")
    @PatchMapping("/{id}")
    public ResponseEntity<Course> patchCourse(@PathVariable Long id, @RequestBody Course course) {
        return ResponseEntity.ok(courseService.patchCourse(id, course));
    }

    @Operation(summary = "Delete a course")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
