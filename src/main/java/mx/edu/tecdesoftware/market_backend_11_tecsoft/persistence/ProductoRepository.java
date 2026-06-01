package mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence;

import mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence.entity.Producto;

import java.util.List;

public class ProductoRepository {

    private ProductoCrudRepository productoCrudRepository;
    
    // SELECT * FROM productos
    public List<Producto> getAll() {
        //Se "castear" de Iterable a la Lista
        return (List<Producto>) productoCrudRepository.findAll();
    }
}
