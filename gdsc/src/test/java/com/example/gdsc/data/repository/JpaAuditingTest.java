package com.example.gdsc.data.repository;

import com.example.gdsc.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaAuditingTest {
    @Autowired
    ProductRepository productRepository;
    @Test
    public void auditingTest(){
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(100);

        Product savedProduct=productRepository.save(product);

        System.out.println("productName: "+savedProduct.getName());
        System.out.println("productName: "+savedProduct.getCreatedAt());
    }
}
