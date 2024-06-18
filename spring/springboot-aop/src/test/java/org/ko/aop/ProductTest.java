package org.ko.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ko.aop.security.CurrentUserHolder;
import org.ko.aop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {

    @Autowired
    ProductService productService;

    @Test
    public void annoInsertTest () {
        CurrentUserHolder.set("tom");
        productService.remove(1L);
    }

    @Test
    public void adminInsertTest () {
        CurrentUserHolder.set("admin");
        productService.remove(1L);
    }
}
