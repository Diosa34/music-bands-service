package com.musicbands.musicbandsservice.schemas.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = OneAttributeIsSetValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface OneAttributeIsSet {
    String message() default "Только один атрибут должен иметь значение";

    Class<?>[] groups() default {}; // Обязательный параметр для групп

    Class<? extends Payload>[] payload() default {};

    String firstAttributeName();

    String secondAttributeName();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        OneAttributeIsSet[] value();
    }
}
