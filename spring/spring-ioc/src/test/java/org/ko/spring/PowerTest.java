package org.ko.spring;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PowerTest {

    @Test
    public void beanTest () {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        HelloTest helloTest = context.getBean(HelloTest.class);
        helloTest.hello();

        PropertyTest propertyTest = context.getBean(PropertyTest.class);
        System.out.printf("PropertyTest name = %s%n", propertyTest.getName());

        ConstructorTest constructorTest = context.getBean(ConstructorTest.class);
        System.out.printf("ConstructorTest name = %s%n", constructorTest.getName());

    }
}
