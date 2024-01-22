package com.example.gdsc.service.imple;


import com.example.gdsc.data.dto.ProductDto;
import com.example.gdsc.data.dto.ProductResponseDto;
import com.example.gdsc.data.entity.Product;
import com.example.gdsc.data.repository.ProductRepository;
import com.example.gdsc.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductServiceimple implements ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceimple.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceimple(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto getProduct(Long number) throws Exception {
        Optional<Product> productOptional = productRepository.findById(number);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            // Product를 ProductResponseDto로 변환하는 로직이 필요함
            return convertToProductResponseDto(product);
        } else {
            // 해당 number에 해당하는 제품이 없을 경우, 예외 처리 또는 기본값 반환 등의 로직을 추가할 수 있음
            return null;
        }

    }
    private ProductResponseDto convertToProductResponseDto(Product product) {
        return ProductResponseDto.builder()
                .number(product.getNumber())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }
    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        LOGGER.info("[saveProduct] productDTO : {}", productDto.toString());
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());

        Product savedProduct = productRepository.save(product);
        LOGGER.info("[saveProduct] savedProduct : {}", savedProduct);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(savedProduct.getNumber());
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setPrice(savedProduct.getPrice());
        productResponseDto.setStock(savedProduct.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) {
        Product foundProduct = productRepository.findById(number).get();
        foundProduct.setName(name);
        Product changedProduct = productRepository.save(foundProduct);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(changedProduct.getNumber());
        productResponseDto.setName(changedProduct.getName());
        productResponseDto.setPrice(changedProduct.getPrice());
        productResponseDto.setStock(changedProduct.getStock());

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) {
        productRepository.deleteById(number);
    }
}