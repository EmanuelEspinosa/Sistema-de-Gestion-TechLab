package com.techlab.ecommerce.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.service.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:5500")
public class ProductoController {
    private final ProductoService service;

    public ProductoController(ProductoService service){
        this.service = service;
    }

    /*MÉTODOS GET */
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos(){
        return ResponseEntity.ok(service.getListaProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable int id){
        return ResponseEntity.ok(service.getProductoPorId(id));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Producto>> buscarPorNombre(@PathVariable String nombre){
        return ResponseEntity.ok(service.buscarPorNombre(nombre));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> buscarPorCategoria (@PathVariable String categoria){
        return ResponseEntity.ok(service.buscarPorCategoria(categoria));
    }

    /*Endpoint para filtrar por precio. Ejemplo: /productos/filtro-precio?min=10000&max=200000*/
    @GetMapping("/filtro-precio")
    public ResponseEntity<List<Producto>> buscarPorRangoPrecio(
            @RequestParam double min, 
            @RequestParam double max) {
        return ResponseEntity.ok(service.listarPorRangoPrecio(min, max));
    }

    @GetMapping("/stock-bajo/{limite}")
    public ResponseEntity<List<Producto>> buscarStockBajo(@PathVariable int limite) {
        return ResponseEntity.ok(service.listarStockBajo(limite));
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<Producto>> buscarPorMarca(@PathVariable String marca) {
        return ResponseEntity.ok(service.listarPorMarca(marca));
    }

    @GetMapping("/mas-caro")
    public ResponseEntity<List<Producto>> buscarMasCaros() {
        return ResponseEntity.ok(service.obtenerProductosMasCaros());
    }

    /*MÉTODOS POST */
    @PostMapping("")
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody Producto producto){
        Producto nuevoProducto = service.guardarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    /*MÉTODOS PUT */
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable int id, @Valid @RequestBody Producto producto){
        return ResponseEntity.ok(service.actualizarProducto(id, producto));
    }

    /*MÉTODOS DELETE */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable int id){
        service.eliminarProducto(id);
        return ResponseEntity.ok().build();
    }
}
