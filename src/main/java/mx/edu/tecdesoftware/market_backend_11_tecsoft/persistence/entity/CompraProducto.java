package mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "compras_productos")
public class CompraProducto {

    @EmbeddedId
    //Viene de nuestra clase CompraProductoPK
    private CompraProductoPK id;

    private Integer cantidad;
    private Double total;
    private Boolean estado;

    // Saber todos los productos que hay en una compra
    @MapsId("idCompra")
    @ManyToOne
    @JoinColumn(name = "id_compra",
            insertable = false, updatable = false)
    private Compra compra;

    @MapsId("idProducto")
    @ManyToOne
    @JoinColumn(name = "id_producto",
            insertable = false, updatable = false)
    private Producto producto;




    public CompraProductoPK getId() {
        return id;
    }

    public void setId(CompraProductoPK id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
