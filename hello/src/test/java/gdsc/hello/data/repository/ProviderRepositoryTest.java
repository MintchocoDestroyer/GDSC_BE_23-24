package gdsc.hello.data.repository;

import gdsc.hello.data.entity.Product;
import gdsc.hello.data.entity.Provider;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProviderRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProviderRepository providerRepository;


    @Test
    void relationshipTest1(){
        Provider provider = new Provider();
        provider.setName("순천향물산");

        providerRepository.save(provider);

        Product product = new Product();
        product.setName("가위");
        product.setPrice(5000);
        product.setStock(500);
        product.setProvider(provider);

        productRepository.save(product);

        System.out.println("productRepository = " + productRepository.findById(1L).orElseThrow(RuntimeException::new));
        System.out.println("provider = " + productRepository.findById(1L).orElseThrow(RuntimeException::new).getProvider());
    }

    @Test
    void relationshipTest(){
        Provider provider = new Provider();
        provider.setName("순천향상사");

        providerRepository.save(provider);

        Product product1 = new Product();
        product1.setName("가위");
        product1.setPrice(5000);
        product1.setStock(500);
        product1.setProvider(provider);

        Product product2 = new Product();
        product2.setName("바위");
        product2.setPrice(30000);
        product2.setStock(300);
        product2.setProvider(provider);

        Product product3 = new Product();
        product3.setName("보");
        product3.setPrice(20000);
        product3.setStock(600);
        product3.setProvider(provider);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> products = providerRepository.findById(provider.getId())
                .get()
                .getProductList();

        for (Product product : products) {
            System.out.println(product);
        }


    }

    @Test
    void cascadeTest(){
        Provider provider = savedProvider("새로운 공급업체");

        Product product1 = savedProduct("상품1", 1000,1000);
        Product product2 = savedProduct("상품2", 3000,4000);
        Product product3 = savedProduct("상품3", 5000,6000);

        provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));

        providerRepository.save(provider);
    }

    @Test
    void orphanRemovalTest(){
        Provider provider = savedProvider("새로운 공급업체");

        Product product1 = savedProduct("상품1", 1000,1000);
        Product product2 = savedProduct("상품2", 3000,4000);
        Product product3 = savedProduct("상품3", 5000,6000);

        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));

        providerRepository.saveAndFlush(provider);
        providerRepository.findAll().forEach(System.out::println);
        productRepository.findAll().forEach(System.out::println);

        Provider foundProvider = providerRepository.findById(1L).get();
        foundProvider.getProductList().remove(0);

        providerRepository.findAll().forEach(System.out::println);
        productRepository.findAll().forEach(System.out::println);
    }

    private Product savedProduct(String name, Integer price, Integer stock) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return product;
    }

    private Provider savedProvider(String name) {
        Provider provider = new Provider();
        provider.setName(name);
        return provider;
    }

}