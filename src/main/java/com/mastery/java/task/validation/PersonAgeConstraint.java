package com.mastery.java.task.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PersonAgeConstraintValidator.class)
public @interface PersonAgeConstraint {
    String message() default "{Employee must be older 18!}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}