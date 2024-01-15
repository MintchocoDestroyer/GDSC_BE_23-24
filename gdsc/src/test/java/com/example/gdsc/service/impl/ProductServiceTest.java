package com.example.gdsc.service.impl;

import com.example.gdsc.data.dao.ProductDAO;
import com.example.gdsc.data.dto.ProductDto;
import com.example.gdsc.data.dto.ProductResponseDto;
import com.example.gdsc.data.entity.Product;
import com.example.gdsc.data.repository.ProductRepository;
import com.example.gdsc.service.ProductService;
import com.example.gdsc.service.imple.ProductServiceimple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@Import({ProductServiceimple.class})
public class ProductServiceTest {
    @MockBean
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Test
    public void getProductTest() throws Exception {
        //given
        Product givenProduct = new Product();
        givenProduct.setNumber(123L);
        givenProduct.setName("펜");
        givenProduct.setPrice(1000);
        givenProduct.setStock(1234);

        Mockito.when(productRepository.findById(123L)).thenReturn(Optional.of(givenProduct));

        //when
        ProductResponseDto productResponseDto = productService.getProduct(123L);

        //then

        Assertions.assertEquals(productResponseDto.getNumber(), givenProduct.getNumber());
        Assertions.assertEquals(productResponseDto.getName(), givenProduct.getName());
        Assertions.assertEquals(productResponseDto.getPrice(), givenProduct.getPrice());
        Assertions.assertEquals(productResponseDto.getStock(), givenProduct.getStock());

        verify(productRepository).findById(123L);
    }
    @Test
    void saveProduct(){
        Mockito.when(productRepository.save(any(Product.class)))
                .then(returnsFirstArg());

        // when
        ProductResponseDto productResponseDto = productService.saveProduct(
                new ProductDto("펜", 1000, 1234));

        // then
        Assertions.assertEquals(productResponseDto.getName(), "펜");
        Assertions.assertEquals(productResponseDto.getPrice(), 1000);
        Assertions.assertEquals(productResponseDto.getStock(), 1234);

        verify(productRepository).save(any());
    }
}
