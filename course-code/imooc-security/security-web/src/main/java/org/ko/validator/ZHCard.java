package org.ko.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义验证注解
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ZHCardValidator.class)
public @interface ZHCard {

    String message() default "银行卡格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
