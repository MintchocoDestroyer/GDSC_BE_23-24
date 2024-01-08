package com.example.gdsc.data.repository;

import com.example.gdsc.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void save() {
        // given
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1000);

        // when
        Product savedProduct = productRepository.save(product);

        // then
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getPrice(), savedProduct.getPrice());
        assertEquals(product.getStock(), savedProduct.getStock());
    }

}