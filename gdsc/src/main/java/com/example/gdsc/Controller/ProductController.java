package com.example.gdsc.Controller;

import com.example.gdsc.data.dto.ChangeProductNameDto;
import com.example.gdsc.data.dto.ProductDto;
import com.example.gdsc.data.dto.ProductResponseDto;
import com.example.gdsc.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ProductResponseDto> getProduct(Long number) throws Exception {
        ProductResponseDto productResponseDto = productService.getProduct(number);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productResponseDto);
    }


    @PostMapping
    public ResponseEntity<ProductResponseDto> createProductName(@RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productResponseDto);
    }


    @PutMapping
    public ResponseEntity<ProductResponseDto> changeProductName(
            @RequestBody ChangeProductNameDto changeProductNameDto
    ) throws Exception {
        ProductResponseDto productResponseDto = productService.changeProductName(
                changeProductNameDto.getNumber(),
                changeProductNameDto.getName()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productResponseDto);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(Long number) throws Exception {
        productService.deleteProduct(number);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("정상적으로 삭제되었습니다.");
    }
}