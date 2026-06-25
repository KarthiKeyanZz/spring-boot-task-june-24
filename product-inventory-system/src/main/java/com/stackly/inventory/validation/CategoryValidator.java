package com.stackly.inventory.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class CategoryValidator implements ConstraintValidator<Category, String> {

    private static final Set<String> ALLOWED =
            Set.of("ELECTRONICS", "GROCERY", "CLOTHING", "FURNITURE", "TOYS");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }
        return ALLOWED.contains(value.toUpperCase());
    }
}
