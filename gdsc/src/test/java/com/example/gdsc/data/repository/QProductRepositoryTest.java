package com.example.gdsc.data.repository;

import com.example.gdsc.data.entity.Product;
import com.example.gdsc.data.entity.QProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class QProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @BeforeEach
    void setPro(){

        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(1000);
        product1.setStock(100);
        product1.setCreatedAt(LocalDateTime.now());
        product1.setUpdatedAt(LocalDateTime.now());

        Product product2 = new Product();
        product2.setName("펜");
        product2.setPrice(5000);
        product2.setStock(300);
        product2.setCreatedAt(LocalDateTime.now());
        product2.setUpdatedAt(LocalDateTime.now());

        Product savedProduct1 = productRepository.save(product1);
        Product savedProduct2 = productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("펜");
        product3.setPrice(3000);
        product3.setStock(300);
        product3.setCreatedAt(LocalDateTime.now());
        product3.setUpdatedAt(LocalDateTime.now());

        Product product4 = new Product();
        product4.setName("펜");
        product4.setPrice(2000);
        product4.setStock(200);
        product4.setCreatedAt(LocalDateTime.now());
        product4.setUpdatedAt(LocalDateTime.now());

        Product product5 = new Product();
        product5.setName("펜");
        product5.setPrice(2500);
        product5.setStock(400);
        product5.setCreatedAt(LocalDateTime.now());
        product5.setUpdatedAt(LocalDateTime.now());

        Product product6 = new Product();
        product6.setName("펜");
        product6.setPrice(3400);
        product6.setStock(500);
        product6.setCreatedAt(LocalDateTime.now());
        product6.setUpdatedAt(LocalDateTime.now());


        Product savedProduct3 = productRepository.save(product3);
        Product savedProduct4 = productRepository.save(product4);
        Product savedProduct5 = productRepository.save(product5);
        Product savedProduct6 = productRepository.save(product6);
    }




    @Autowired
    QProductRepository qProductRepository;
    @Test
    public void queryDSLTest(){
        QProduct qProduct = QProduct.product;
        Iterable<Product> productList = qProductRepository.findAll(
                qProduct.name.contains("펜")
                        .and(qProduct.price.between(2000,2500))
        );
        for(Product product: productList){
            System.out.println("-------------------------");
            System.out.println(product.getNumber());
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getStock());
            System.out.println("-------------------------");
        }
    }
}
