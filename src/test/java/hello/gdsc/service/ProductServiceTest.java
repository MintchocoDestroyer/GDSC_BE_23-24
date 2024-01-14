package hello.gdsc.service;

import hello.gdsc.data.repository.ProductRepository;
import hello.gdsc.service.impl.ProductServiceImpl;
import org.mockito.Mockito;

public class ProductServiceTest {

    private ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private ProductServiceImpl productService;


}
