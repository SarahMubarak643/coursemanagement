package com.example.coursemanagement.repository;

import com.example.coursemanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for Course entities.
 * JpaRepository already provides save, findAll, findById, deleteById, etc.
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

    // Custom finder required by the assignment
    List<Course> findByCategory(String category);
}
