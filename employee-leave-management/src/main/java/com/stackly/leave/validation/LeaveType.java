package com.stackly.leave.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = LeaveTypeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LeaveType {

    String message() default "Leave type must be one of: CASUAL, SICK, EARNED, MATERNITY, PATERNITY";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
