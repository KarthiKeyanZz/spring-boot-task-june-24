package com.stackly.inventory.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = CategoryValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Category {

    String message() default "Category must be one of: ELECTRONICS, GROCERY, CLOTHING, FURNITURE, TOYS";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
