package com.stackly.studentcourse.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = CollegeEmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CollegeEmail {

    String message() default "Email must belong to the college domain (@stackly.edu)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
