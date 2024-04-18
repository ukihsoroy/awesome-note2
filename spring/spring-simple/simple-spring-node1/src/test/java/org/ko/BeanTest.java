package org.ko;

import org.junit.jupiter.api.Test;
import org.ko.simple.spring.ioc.BeanDefinition;
import org.ko.simple.spring.ioc.BeanFactory;

public class BeanTest {

    @Test
    public void test1 () {
        BeanFactory factory = new BeanFactory();
        BeanDefinition definition = new BeanDefinition(new HelloService());
        factory.registerBeanDefinition("helloService", definition);
        HelloService service = HelloService.class.cast(factory.getBean("helloService"));
        service.print();
    }
}
