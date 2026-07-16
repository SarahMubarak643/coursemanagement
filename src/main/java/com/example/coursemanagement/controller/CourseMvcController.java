package com.example.coursemanagement.controller;

import com.example.coursemanagement.entity.Course;
import com.example.coursemanagement.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Simple Spring MVC + Thymeleaf controller.
 * Displays all courses in an HTML table and provides a form to add a new one.
 */
@Controller
@RequestMapping("/courses")
public class CourseMvcController {

    private final CourseService courseService;

    // Custom property injected from application.properties (training.system.name=COOP Training System)
    @Value("${training.system.name}")
    private String systemName;

    public CourseMvcController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Trims empty strings to null on every form submission handled by this controller.
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses(null));
        model.addAttribute("systemName", systemName);
        return "courses";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        return "add-course";
    }

    @PostMapping("/add")
    public String addCourse(@Valid @ModelAttribute("course") Course course, BindingResult result) {
        if (result.hasErrors()) {
            return "add-course";
        }
        courseService.createCourse(course);
        return "redirect:/courses";
    }
}
