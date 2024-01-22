package com.example.gdsc.data.repository;

import com.example.gdsc.data.entity.Product;
import com.example.gdsc.data.entity.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProviderRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProviderRepository providerRepository;

    @Test
    void relationshipTest1(){
        Provider provider = new Provider();
        provider.setName("GDSC용품");

        providerRepository.save(provider);

        Product product = new Product();
        product.setName("가위");
        product.setPrice(5000);
        product.setStock(500);;
        product.setProvider(provider);

        productRepository.save(product);

        System.out.println("product : "+
                productRepository.findById(1L).orElseThrow(RuntimeException::new));

        System.out.println("provider : "+
                productRepository.findById(1L).orElseThrow(RuntimeException::new).getProvider());
    }
    //다대일 양방향 매핑
    @Test
    void relationshipTest(){
        Provider provider = new Provider();
        provider.setName("GDSC용품");

        providerRepository.save(provider);

        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(2000);
        product1.setStock(100);;
        product1.setProvider(provider);

        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("가방");
        product2.setPrice(20000);
        product2.setStock(200);;
        product2.setProvider(provider);

        productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("노트");
        product3.setPrice(3000);
        product3.setStock(1000);;
        product3.setProvider(provider);

        productRepository.save(product3);

        //무시 - 연관관계의 주인이 아니기 때문에 외래키 관리 불가
        provider.getProductList().add(product1);

        List<Product> products = providerRepository.findById(provider.getId()).get().getProductList();

        for(Product product:products){
            System.out.println(product);
        }

    }
}
