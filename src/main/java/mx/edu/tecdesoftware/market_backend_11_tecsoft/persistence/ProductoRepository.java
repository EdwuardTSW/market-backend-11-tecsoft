package mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence;

import mx.edu.tecdesoftware.market_backend_11_tecsoft.domain.Product;
import mx.edu.tecdesoftware.market_backend_11_tecsoft.domain.repository.ProductRepository;
import mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence.entity.Producto;
import mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
// Le dices a Spring que esta clase se comunicara con la base de datos
public class ProductoRepository implements ProductRepository {

    private ProductoCrudRepository productoCrudRepository;
    private ProductMapper productMapper;

    // SELECT * FROM productos

    public List<Product> getAll() {
        //Se "castear" de Iterable a la Lista
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(productos);
    }

    public  Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(productos));
    }

    /*

    SELECT * FROM producto
    WHERE cantidad_stock < ?
    ND estado = true;

    */

    public Optional<List<Product>> getScarceProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return Optional.of(productMapper.toProducts(productos.get()));
    }

    //Obtener un producto dado el id
    public Optional<Product> getProduct(Product product) {
        return productoCrudRepository.findById(product.getProductId())
                .map(producto ->  productMapper.toProduct(producto));
    }

    /*
    INSERT INTO producto (nombre, cantidad_stock, estado, id_categoria)
    VALUES (?, ?, ?, ?);
    */


    //Guardar un Producto
    public Product save(Product product) {
        Producto producto = productMapper.toProducto(product);
        return productMapper.toProduct(productoCrudRepository.save(producto));
    }

    //Eliminar por id
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }

}
