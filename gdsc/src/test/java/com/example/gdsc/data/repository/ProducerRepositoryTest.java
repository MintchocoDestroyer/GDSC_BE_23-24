package com.example.gdsc.data.repository;

import com.example.gdsc.data.entity.Producer;
import com.example.gdsc.data.entity.Product;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ProducerRepositoryTest {

    @Autowired
    ProducerRepository producerRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    //트랜잭션 연결이 끊어져 엔티티끼리 데이터 전달 불가능 방지
    @Transactional
    //다대다 단방향 테스트
    void relationshipTest(){
        Product product1 = saveProduct("동글펜",500,1000);
        Product product2 = saveProduct("네모 공책",100,2000);
        Product product3 = saveProduct("지우개",152,1234);

        Producer producer1 = saveProducer("flature");
        Producer producer2 = saveProducer("wikibooks");

        producer1.addProduct(product1);
        producer1.addProduct(product2);

        producer2.addProduct(product1);
        producer2.addProduct(product1);

        producerRepository.saveAll(Lists.newArrayList(producer1, producer2));

        System.out.println(producerRepository.findById(1L).get().getProducts());
    }

    @Test
    //트랜잭션 연결이 끊어져 엔티티끼리 데이터 전달 불가능 방지
    @Transactional
        //다대다 양방향 테스트
    void relationshipTest2(){
        Product product1 = saveProduct("동글펜",500,1000);
        Product product2 = saveProduct("네모 공책",100,2000);
        Product product3 = saveProduct("지우개",152,1234);

        Producer producer1 = saveProducer("flature");
        Producer producer2 = saveProducer("wikibooks");

        producer1.addProduct(product1);
        producer1.addProduct(product2);

        producer2.addProduct(product1);
        producer2.addProduct(product1);

        product1.addProducer(producer1);
        product2.addProducer(producer1);
        product2.addProducer(producer2);
        product3.addProducer(producer2);

        producerRepository.saveAll(Lists.newArrayList(producer1, producer2));
        productRepository.saveAll(Lists.newArrayList(product1, product2, product3));

        System.out.println("producer : " + producerRepository.findById(1L).get().getProducts());

        System.out.println("products : " + productRepository.findById(2L).get().getProducers());
    }

    private Producer saveProducer(String name) {
        Producer producer = new Producer();
        producer.setName(name);

        return producerRepository.save(producer);
    }

    private Product saveProduct(String name, Integer price, Integer stock) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        return productRepository.save(product);
    }
}
