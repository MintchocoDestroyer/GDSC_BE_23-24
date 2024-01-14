package hello.gdsc.service.impl;

import hello.gdsc.data.dao.ProductDAO;
import hello.gdsc.data.dto.ChangeProductNameDto;
import hello.gdsc.data.dto.ProductDto;
import hello.gdsc.data.dto.ProductResponseDto;
import hello.gdsc.data.entity.Product;
import hello.gdsc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }



    @Override
    public ProductResponseDto getProduct(Long number) {
        Product product = productDAO.selectProduct(number);

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {

        Product product = new Product();

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreateAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product saveProduct = productDAO.insertProduct(product);

        return ProductResponseDto
                .builder()
                .number(saveProduct.getNumber())
                .name(saveProduct.getName())
                .price(saveProduct.getPrice())
                .stock(saveProduct.getStock())
                .build();
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {

        Product changedProduct = productDAO.updateProductName(number, name);

        return ProductResponseDto
                .builder()
                .number(changedProduct.getNumber())
                .name(changedProduct.getName())
                .price(changedProduct.getPrice())
                .stock(changedProduct.getStock())
                .build();
    }

    @Override
    public void deleteProduct(Long number) throws Exception {

        productDAO.deleteProduct(number);
    }
}
