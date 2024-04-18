package org.ko.mvc.annotation;

import java.lang.annotation.Documented;  
import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
  
/** 
 * 注解Service 
 * @author K.O 
 * @Date 2017年9月2日15:32:08
 * @motto 配置类为service 
 * @Version 1.0 
 */  
@Target({ ElementType.TYPE })  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface Service {  
    String value() default "";  
}
