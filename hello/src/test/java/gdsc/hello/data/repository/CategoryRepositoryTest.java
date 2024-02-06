package gdsc.hello.data.repository;

import gdsc.hello.data.entity.Category;
import gdsc.hello.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void relationshipTest(){
        Product product= new Product();
        product.setName("가위");
        product.setPrice(5000);
        product.setStock(500);

        productRepository.save(product);

        Category category = new Category();
        category.setCode("S1");
        category.setName("도서");
        category.getProducts().add(product);

        categoryRepository.save(category);

        List<Product> productList = categoryRepository.findById(1L).get().getProducts();

        for (Product product1 : productList) {
            System.out.println("product1 = " + product1);
        }

    }

}