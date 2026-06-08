package mx.edu.tecdesoftware.market_backend_11_tecsoft.domain.repository;

import mx.edu.tecdesoftware.market_backend_11_tecsoft.domain.Product;
import mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence.entity.Categoria;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarceProducts(int quatity);
    Optional<Product> getProduct(Product product);
    Product save(Product product);
    void delete(int productId);
}
