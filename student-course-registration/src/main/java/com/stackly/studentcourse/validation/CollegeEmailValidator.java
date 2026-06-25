package com.stackly.studentcourse.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CollegeEmailValidator implements ConstraintValidator<CollegeEmail, String> {

    private static final String DOMAIN = "@stackly.edu";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }
        return value.toLowerCase().endsWith(DOMAIN);
    }
}
