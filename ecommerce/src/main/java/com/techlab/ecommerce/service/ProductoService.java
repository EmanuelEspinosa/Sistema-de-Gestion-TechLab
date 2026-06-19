package com.techlab.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import com.techlab.ecommerce.exception.PrecioInvalidoException;
import com.techlab.ecommerce.exception.ProductoNoEncontradoException;
import com.techlab.ecommerce.exception.StockInsuficienteException;
import com.techlab.ecommerce.model.Producto;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    private List<Producto> productos = new ArrayList<>();
    private static int counterId = 1;

    public Producto guardarProducto(Producto p) {

        validarProducto(p);

        p.setId(counterId);
        productos.add(p);
        counterId++;
        return p;
    }

    // Listar productos
    public List<Producto> getListaProductos() {
        return productos;
    }

    // Buscar producto por Id
    public Producto getProductoPorId(int id) {
        for (Producto p : this.productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new ProductoNoEncontradoException(String.format("No se encontro el producto con Id %d", id));
    }

    // Actualizar los datos de un producto existente
    public Producto actualizarProducto(int id, Producto nuevoProducto) {

        validarProducto(nuevoProducto);

        Producto p = getProductoPorId(id);

        p.setNombre(nuevoProducto.getNombre());
        p.setPrecio(nuevoProducto.getPrecio());
        p.setStock(nuevoProducto.getStock());
        p.setCategoria(nuevoProducto.getCategoria());
        p.setMarca(nuevoProducto.getMarca());

        return p;
    }

    // Eliminar producto existente por Id
    public void eliminarProducto(int id) {
        Producto p = getProductoPorId(id);
        productos.remove(p);
    }

    private void validarProducto(Producto p) {
        if (p.getNombre() == null || p.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacio");
        }
        if (p.getPrecio() <= 0) {
            throw new PrecioInvalidoException("El precio debe ser mayor a cero. Se recibio: " + p.getPrecio());
        }
        if (p.getStock() < 0) {
            throw new StockInsuficienteException("El stock no puede ser negativo. Se recibio " + p.getStock());
        }
    }
}
