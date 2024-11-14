package com.musicbands.musicbandsservice.schemas.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class OneAttributeIsSetValidator implements ConstraintValidator<OneAttributeIsSet, Object> {
    private String firstAttributeName;
    private String secondAttributeName;

    public void initialize(OneAttributeIsSet constraintAnnotation) {
        this.firstAttributeName = constraintAnnotation.firstAttributeName();
        this.secondAttributeName = constraintAnnotation.secondAttributeName();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object value1 = new BeanWrapperImpl(value).getPropertyValue(firstAttributeName);
        Object value2 = new BeanWrapperImpl(value).getPropertyValue(secondAttributeName);

        return (value1 == null && value2 != null) || (value1 != null && value2 == null);
    }
}
