package mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence.crud;

import mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoCrudRepository
        extends CrudRepository<Producto, Integer> {


}
