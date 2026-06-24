package com.stackly.studentcourse.controller;

import com.stackly.studentcourse.dto.CourseUpdateRequest;
import com.stackly.studentcourse.dto.StudentRequest;
import com.stackly.studentcourse.model.Student;
import com.stackly.studentcourse.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Course Registration", description = "Register students and manage their course enrollment")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "Register a new student")
    @PostMapping
    public ResponseEntity<Student> register(@Valid @RequestBody StudentRequest request) {
        Student created = studentService.register(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Operation(summary = "View all registered students")
    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @Operation(summary = "Find a student by id")
    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findById(id));
    }

    @Operation(summary = "Fully update a student registration")
    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @Valid @RequestBody StudentRequest request) {
        return ResponseEntity.ok(studentService.update(id, request));
    }

    @Operation(summary = "Partially update a student's enrolled course")
    @PatchMapping("/{id}/course")
    public ResponseEntity<Student> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseUpdateRequest request) {
        return ResponseEntity.ok(studentService.updateCourse(id, request));
    }

    @Operation(summary = "Delete a student registration")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
