package com.rikkei.baith5.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LotSizeValidator implements ConstraintValidator<LotSize, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return value % 100 == 0 && value > 0;
    }
}