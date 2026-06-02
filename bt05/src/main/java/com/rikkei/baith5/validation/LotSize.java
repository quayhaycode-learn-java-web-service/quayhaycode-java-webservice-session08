package com.rikkei.baith5.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LotSizeValidator.class)
@Documented
public @interface LotSize {
    String message() default "Khối lượng đặt lệnh phải là bội số của 100 (Ví dụ: 100, 200, 300...)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}