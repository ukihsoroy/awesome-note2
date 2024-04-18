package org.ko.mvc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * Autowired注解 
 * @author K.O 
 * @Date 2017年9月2日15:29:47
 * @motto 自动注入bean实例
 * @Version 1.0 
 */  
@Target({ ElementType.FIELD }) // 代表注解的注解  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface Autowired {
	String value() default "";
}
