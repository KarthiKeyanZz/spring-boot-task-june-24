package com.stackly.movie.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = SeatNumberValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SeatNumber {

    String message() default "Seat number must be a row letter followed by 1-2 digits (e.g. A12)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
