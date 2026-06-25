package com.stackly.recharge.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class OperatorCodeValidator implements ConstraintValidator<OperatorCode, String> {

    private static final Set<String> ALLOWED = Set.of("AIRTEL", "JIO", "VI", "BSNL");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }
        return ALLOWED.contains(value.toUpperCase());
    }
}
