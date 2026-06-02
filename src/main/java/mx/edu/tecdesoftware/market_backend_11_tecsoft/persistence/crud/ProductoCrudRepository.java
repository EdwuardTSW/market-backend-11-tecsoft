package mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence.crud;

import mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    // Query method
    /*
        SELECT *
        FROM Categorias
        WHERE id_categoria = ?
        ORDER BY nombre ASC
     */
    // Obtener una lista de productos filtrados por id de categoria
    // y ordenados ascendentemente por nombre
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    // Obtener los productos escasos
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidad, boolean estado);


}
