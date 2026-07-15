package mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence;

import mx.edu.tecdesoftware.market_backend_11_tecsoft.domain.Purchase;
import mx.edu.tecdesoftware.market_backend_11_tecsoft.domain.repository.PurchaseRepository;
import mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence.crud.CompraCrudRepository;
import mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence.entity.Compra;
import mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence.entity.CompraProducto;
import mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence.entity.Producto;
import mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence.mapper.PurchaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    private final CompraCrudRepository compraCrudRepository;
    private final ProductoCrudRepository productoCrudRepository;
    private final PurchaseMapper purchaseMapper;

    public CompraRepository(
            CompraCrudRepository compraCrudRepository,
            ProductoCrudRepository productoCrudRepository,
            PurchaseMapper purchaseMapper
    ) {
        this.compraCrudRepository = compraCrudRepository;
        this.productoCrudRepository = productoCrudRepository;
        this.purchaseMapper = purchaseMapper;
    }

    @Override
    public List<Purchase> getAll() {
        List<Compra> compras = (List<Compra>) compraCrudRepository.findAll();
        return purchaseMapper.toPurchases(compras);
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        List<Compra> compras = compraCrudRepository.findByIdCliente(clientId);
        return Optional.of(purchaseMapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = purchaseMapper.toCompra(purchase);

        if (compra.getIdCompra() != null && compra.getIdCompra() == 0) {
            compra.setIdCompra(null);
        }

        if (compra.getProductos() != null) {
            compra.getProductos().forEach(producto -> preparePurchaseItem(compra, producto));
        }

        Compra compraGuardada= compraCrudRepository.save(compra);
        return purchaseMapper.toPurchase(compraGuardada);
    }

    private void preparePurchaseItem(Compra compra, CompraProducto item) {
        item.setCompra(compra);

        if (item.getId() == null || item.getId().getIdProducto() == null) {
            throw new IllegalArgumentException(
                    "El item de compra debe incluir un productId"
            );
        }

        Integer productId = item.getId().getIdProducto();

        Producto producto = productoCrudRepository.findById(productId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No existe el producto con ID: " + productId
                        )
                );

        item.setProducto(producto);

        if (item.getId().getIdCompra() != null
                && item.getId().getIdCompra().equals(0)) {
            item.getId().setIdCompra(null);
        }
    }
}