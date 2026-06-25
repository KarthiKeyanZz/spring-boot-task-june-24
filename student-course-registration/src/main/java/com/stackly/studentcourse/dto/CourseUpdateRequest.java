package com.stackly.studentcourse.dto;

import jakarta.validation.constraints.NotBlank;

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
