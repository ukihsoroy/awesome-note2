package org.ko.validator;

import org.ko.web.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义验证注解
 */
public class ZHCardValidator implements ConstraintValidator<ZHCard, Object> {

    @Autowired private HelloService helloService;

    @Override
    public void initialize(ZHCard constraintAnnotation) {
        System.out.println("ZHCard validator init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println(value);
        helloService.hello("K.O");
        return false;
    }
}
