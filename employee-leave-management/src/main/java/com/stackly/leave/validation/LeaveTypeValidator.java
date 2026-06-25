package com.stackly.leave.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class LeaveTypeValidator implements ConstraintValidator<LeaveType, String> {

    private static final Set<String> ALLOWED =
            Set.of("CASUAL", "SICK", "EARNED", "MATERNITY", "PATERNITY");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }
        return ALLOWED.contains(value.toUpperCase());
    }
}
