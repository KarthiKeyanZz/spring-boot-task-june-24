package com.stackly.recharge.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = OperatorCodeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperatorCode {

    String message() default "Operator must be one of: AIRTEL, JIO, VI, BSNL";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
