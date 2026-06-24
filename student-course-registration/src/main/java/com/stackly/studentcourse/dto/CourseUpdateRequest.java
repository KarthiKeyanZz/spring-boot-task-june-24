package com.stackly.studentcourse.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Partial update payload used by the PATCH endpoint to change a student's enrolled course.
 */
public class CourseUpdateRequest {

    @NotBlank(message = "Course name cannot be empty")
    private String courseName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
