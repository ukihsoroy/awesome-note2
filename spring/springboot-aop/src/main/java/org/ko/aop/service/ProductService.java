package org.ko.aop.service;

import org.ko.aop.domain.Product;
import org.ko.aop.security.AdminOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired AuthService authService;

    @AdminOnly
    public void insert (Product product) {
//        authService.checkAccess();
        System.out.println("insert product");
    }

    @AdminOnly
    public void remove (Long id) {
//        authService.checkAccess();
        System.out.println("remove product");
    }

}
