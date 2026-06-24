package com.stackly.studentcourse.service;

import com.stackly.studentcourse.dto.CourseUpdateRequest;
import com.stackly.studentcourse.dto.StudentRequest;
import com.stackly.studentcourse.exception.DuplicateEmailException;
import com.stackly.studentcourse.exception.StudentNotFoundException;
import com.stackly.studentcourse.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {

    private final Map<Long, Student> store = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(0);

    public Student register(StudentRequest request) {
        boolean emailTaken = store.values().stream()
                .anyMatch(s -> s.getEmail().equalsIgnoreCase(request.getEmail()));
        if (emailTaken) {
            throw new DuplicateEmailException(request.getEmail());
        }
        long id = idSequence.incrementAndGet();
        Student student = new Student(id, request.getStudentName(), request.getCourseName(),
                request.getEmail(), request.getAge());
        store.put(id, student);
        return student;
    }

    public List<Student> findAll() {
        return new ArrayList<>(store.values());
    }

    public Student findById(Long id) {
        Student student = store.get(id);
        if (student == null) {
            throw new StudentNotFoundException(id);
        }
        return student;
    }

    public Student update(Long id, StudentRequest request) {
        Student student = findById(id);
        store.values().stream()
                .filter(s -> !s.getStudentId().equals(id))
                .filter(s -> s.getEmail().equalsIgnoreCase(request.getEmail()))
                .findAny()
                .ifPresent(s -> { throw new DuplicateEmailException(request.getEmail()); });
        student.setStudentName(request.getStudentName());
        student.setCourseName(request.getCourseName());
        student.setEmail(request.getEmail());
        student.setAge(request.getAge());
        return student;
    }

    public Student updateCourse(Long id, CourseUpdateRequest request) {
        Student student = findById(id);
        student.setCourseName(request.getCourseName());
        return student;
    }

    public void delete(Long id) {
        if (store.remove(id) == null) {
            throw new StudentNotFoundException(id);
        }
    }
}
