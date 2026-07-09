package mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence.mapper;

import mx.edu.tecdesoftware.market_backend_11_tecsoft.domain.PurchaseItem;
import mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence.entity.CompraProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source = "id.idCompra", target = "purchaseId"),
            @Mapping(source = "id.idProducto", target = "productId"),
            @Mapping(source = "cantidad", target = "quantity"),
            @Mapping(source = "estado", target = "status")
    })
    PurchaseItem toPurchaseItem(CompraProducto producto);
    List<PurchaseItem> toPurchaseItems(List<CompraProducto> productos);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true)
    })
    CompraProducto toCompraProducto(PurchaseItem item);
}
