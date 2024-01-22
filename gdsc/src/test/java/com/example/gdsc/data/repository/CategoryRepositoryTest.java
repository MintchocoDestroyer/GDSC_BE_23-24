package com.example.gdsc.data.repository;

import com.example.gdsc.data.entity.Category;
import com.example.gdsc.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
//일대다 단방향 매핑
public class CategoryRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void relationshipTest(){
        Product product = new Product();
        product.setName("펜");
        product.setPrice(2000);
        product.setStock(100);

        productRepository.save(product);

        Category category = new Category();
        category.setCode("S1");
        category.setName("도서");
        category.getProducts().add(product);

        categoryRepository.save(category);

        //테스트
        List<Product> products = categoryRepository.findById(1L).get().getProducts();

        for(Product foundProduct : products){
            System.out.println(products);
        }
    }


}
