package gdsc.hello.service;

import gdsc.hello.data.dto.ProductDto;
import gdsc.hello.data.dto.ProductResponseDto;
import gdsc.hello.data.entity.Product;
import gdsc.hello.data.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


class ProductServiceImplTest {

    private ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUpTest(){
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void getProductTest(){
        //given
        Product givenProduct = new Product();
        givenProduct.setNumber(123L);
        givenProduct.setName("펜");
        givenProduct.setPrice(1000);
        givenProduct.setStock(1234);

        //when
        Mockito.when(productRepository.findById(123L))
                .thenReturn(Optional.of(givenProduct));

        ProductResponseDto productResponseDto = productService.getProduct(123L);

        //then
        assertEquals(productResponseDto.getNumber(), givenProduct.getNumber());
        assertEquals(productResponseDto.getName(), givenProduct.getName());
        assertEquals(productResponseDto.getPrice(), givenProduct.getPrice());
        assertEquals(productResponseDto.getStock(), givenProduct.getStock());

        verify(productRepository).findById(123L);
    }

    @Test
    void saveProductTest(){
        Mockito.when(productRepository.save(any(Product.class)))
                .then(returnsFirstArg());

        ProductResponseDto productResponseDto = productService.saveProduct(
                new ProductDto("펜", 1000, 1234));

        assertEquals(productResponseDto.getName(), "펜");
        assertEquals(productResponseDto.getPrice(), 1000);
        assertEquals(productResponseDto.getStock(), 1234);

        verify(productRepository).save(any());
    }

}