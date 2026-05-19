package mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CollectionId;

@Entity
@Table(name = "productos" )
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    private String nombre;

    @Column(name = "id_categoria")
    private Integer id_categoria;

    @Column(name = "codigo_barras")
    private Integer precioVenta;

    @Column(name = "precio_venta")
    private Integer id_categoria;

    @Column(name = "cantidad_stock")
    private Integer cantidadStock;

    private Boolean estado;

}
