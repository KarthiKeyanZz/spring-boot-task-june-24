package com.stackly.movie.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SeatNumberValidator implements ConstraintValidator<SeatNumber, String> {

    private static final String PATTERN = "^[A-Z][0-9]{1,2}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }
        return value.matches(PATTERN);
    }
}
