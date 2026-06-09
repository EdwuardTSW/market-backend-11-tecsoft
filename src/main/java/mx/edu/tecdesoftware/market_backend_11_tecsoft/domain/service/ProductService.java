package mx.edu.tecdesoftware.market_backend_11_tecsoft.domain.service;

import mx.edu.tecdesoftware.market_backend_11_tecsoft.domain.Product;
import mx.edu.tecdesoftware.market_backend_11_tecsoft.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId) {
        return productRepository.getProduct(new Product() {{
            setProductId(productId);
        }});
    }

    public Optional<List<Product>> getByCategory(int categoryId) {
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public boolean delete(int idProducto) {

        if (getProduct(idProducto).isPresent()) {
            productRepository.delete(idProducto);
            return true;
        } else {
            return false;
        }
    }
}