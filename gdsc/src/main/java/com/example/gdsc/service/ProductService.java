package com.example.gdsc.service;

import com.example.gdsc.data.dto.ProductDto;
import com.example.gdsc.data.dto.ProductResponseDto;
import com.example.gdsc.data.repository.ProductRepository;

public interface ProductService {

    ProductResponseDto getProduct(Long number);
    ProductResponseDto saveProduct(ProductDto productDto);
    ProductResponseDto changeProductName(Long number, String name) throws Exception;
    void deleteProduct(Long number) throws Exception;
}
