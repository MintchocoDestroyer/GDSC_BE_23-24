package hello.gdsc.data.dao.impl;

import hello.gdsc.data.dao.ProductDAO;
import hello.gdsc.data.entity.Product;
import hello.gdsc.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product insertProduct(Product product) {
        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Override
    public Product selectProduct(Long number) {
        Optional<Product> selectedProduct = productRepository.findById(number);

        return selectedProduct.orElseThrow(() -> new RuntimeException("조회 안됨"));
    }

    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        Product updatedProduct;
        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();

            product.setName(name);
            product.setUpdatedAt(LocalDateTime.now());

            //save를 통해 JPA에서 더티체크 라는 변경감지를 하고,
            //변경이 감지되면 대상 객체에 해당하는 데이터베이스 레코드를 업데이트한다.
            updatedProduct = productRepository.save(product);
        } else {
            throw new Exception();
        }
        return updatedProduct;
    }


    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        if(selectedProduct.isPresent()){
            Product product = selectedProduct.get();

            productRepository.delete(product);
        }else{
            throw new Exception();
        }
    }
}
