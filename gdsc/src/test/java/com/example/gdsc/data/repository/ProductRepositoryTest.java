package com.example.gdsc.data.repository;

import com.example.gdsc.config.QueryDSLConfiguration;
import com.example.gdsc.data.entity.Product;
import com.example.gdsc.data.entity.QProduct;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@WebAppConfiguration
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Import(QueryDSLConfiguration.class)
public class ProductRepositoryTest {
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




    @Test
    void saveTest(){
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1000);

        Product savedProduct = productRepository.save(product);

        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getPrice(), savedProduct.getPrice());
        assertEquals(product.getStock(), savedProduct.getStock());
    }
    @Test
    void selectTest() {
        // given
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1000);

        Product savedProduct = productRepository.saveAndFlush(product);

        // when
        Product foundProduct = productRepository.findById(savedProduct.getNumber()).get();

        // then
        assertEquals(product.getName(), foundProduct.getName());
        assertEquals(product.getPrice(), foundProduct.getPrice());
        assertEquals(product.getStock(), foundProduct.getStock());
    }

    //쿼리 메서드
    @Test
    void sortingAndPagingTest(){

        List<Product> productList2 = productRepository.findByName("펜", Sort.by(Sort.Order.asc("price"), Sort.Order.desc("stock")));
        //System.out.println(productList2);

        Page<Product> productPage = productRepository.findByName("펜", PageRequest.of(0, 2));
        Page<Product> productPage1 = productRepository.findByName("펜", PageRequest.of(1, 2));
        Page<Product> productPage2 = productRepository.findByName("펜", PageRequest.of(2, 2));
        System.out.println(productPage.getContent());
        System.out.println(productPage1.getContent());
        System.out.println(productPage2.getContent());


    }
    @PersistenceContext
    EntityManager entityManager;

    //querydsl 테스트
    @Test
    void queryDslTest(){
        JPAQuery<Product> query = new JPAQuery(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = query
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();
        for (Product product:productList){
            System.out.println("--------------------");
            System.out.println();
            System.out.println("Product Number : "+product.getNumber());
            System.out.println("Product Name : "+product.getName());
            System.out.println("Product Price : "+product.getPrice());
            System.out.println("Product Stock : "+product.getStock());
            System.out.println();
            System.out.println("--------------------");
        }
    }

    //JPAQueryFactory
    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    void queryDslTest4(){

        QProduct qProduct = QProduct.product;

        List<Integer> productList = jpaQueryFactory
                .select(qProduct.price)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(Integer product:productList){
            System.out.println("---------------------");
            System.out.println("Product Price : " + product);
            System.out.println("---------------------");
        }
    }
}
