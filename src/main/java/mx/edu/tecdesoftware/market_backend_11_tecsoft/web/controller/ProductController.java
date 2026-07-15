package mx.edu.tecdesoftware.market_backend_11_tecsoft.web.controller;


import mx.edu.tecdesoftware.market_backend_11_tecsoft.domain.Product;
import mx.edu.tecdesoftware.market_backend_11_tecsoft.domain.service.ProductService;
import mx.edu.tecdesoftware.market_backend_11_tecsoft.persistence.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("")
    public ResponseEntity<List> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int productId){
        return productService.getProduct(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("categoryId") int categoryId){
        return productService.getByCategory(categoryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete(@PathVariable("productId") Integer productId){
        if (productService.delete(productId)){
            return ResponseEntity.ok().build();
        } return ResponseEntity.notFound().build();
    }

}