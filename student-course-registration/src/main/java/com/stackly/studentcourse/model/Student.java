package com.stackly.studentcourse.model;

public class Student {

    private Long studentId;
    private String studentName;
    private String courseName;
    private String email;
    private Integer age;

    public Student() {
    }

    public Student(Long studentId, String studentName, String courseName, String email, Integer age) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseName = courseName;
        this.email = email;
        this.age = age;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
