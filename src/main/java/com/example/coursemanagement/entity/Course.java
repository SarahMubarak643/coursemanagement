package com.example.coursemanagement.entity;

import com.example.coursemanagement.validation.ValidLevel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is required")
    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @NotBlank(message = "category is required")
    @Column(name = "category", nullable = false, length = 100)
    private String category;

    @NotNull(message = "price is required")
    @Min(value = 0, message = "price must be at least 0")
    @Max(value = 100000, message = "price must be at most 100000")
    @Column(name = "price", nullable = false)
    private Double price;

    @NotNull(message = "durationHours is required")
    @Min(value = 1, message = "durationHours must be at least 1")
    @Max(value = 1000, message = "durationHours must be at most 1000")
    @Column(name = "duration_hours", nullable = false)
    private Integer durationHours;

    @ValidLevel
    @Column(name = "level", nullable = false, length = 20)
    private String level;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @NotBlank(message = "code is required")
    @Pattern(regexp = "^[A-Z]{3}-\\d{3}$", message = "code must match the pattern ABC-101")
    @Column(name = "code", nullable = false, unique = true, length = 10)
    private String code;

    public Course() {
    }

    public Course(Long id, String name, String category, Double price, Integer durationHours,
                  String level, Boolean active, String code) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.durationHours = durationHours;
        this.level = level;
        this.active = active;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(Integer durationHours) {
        this.durationHours = durationHours;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
