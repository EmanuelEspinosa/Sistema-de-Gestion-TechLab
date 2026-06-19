package com.techlab.ecommerce.controller;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService service;

    public ProductoController(ProductoService service){
        this.service = service;
    }

    @GetMapping
    public List<Producto> listarProductos(){
        return service.getListaProductos();
    }
}
