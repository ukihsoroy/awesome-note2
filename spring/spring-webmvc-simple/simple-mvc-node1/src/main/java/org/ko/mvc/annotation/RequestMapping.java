package org.ko.mvc.annotation;

import java.lang.annotation.Documented;  
import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
  
/** 
 * RequestMapping注解 
 * @author K.O 
 * @Date 2017年9月2日15:29:59
 * @motto 访问的url对应的接口路径 
 * @Version 1.0 
 */  
@Target({ ElementType.METHOD,  ElementType.TYPE}) // 在方法上的注解  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface RequestMapping {  
    String value() default "";  
}