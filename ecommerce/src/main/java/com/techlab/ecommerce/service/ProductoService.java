package com.techlab.ecommerce.service;

import java.util.List;

// import com.techlab.ecommerce.exception.PrecioInvalidoException;
import com.techlab.ecommerce.exception.ProductoNoEncontradoException;
// import com.techlab.ecommerce.exception.StockInsuficienteException;
import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.repository.ProductoRepository;

import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public Producto guardarProducto(Producto p) {
        // validarProducto(p);
        return repository.save(p);
    }

    // Listar productos
    public List<Producto> getListaProductos() {
        return repository.findAll();
    }

    // Buscar producto por Id
    public Producto getProductoPorId(int id) {
        return repository.findById(id).orElseThrow(
                () -> new ProductoNoEncontradoException(String.format("No se encontro el producto con Id %d", id)));
    }

    // Actualizar los datos de un producto existente
    public Producto actualizarProducto(int id, Producto nuevoProducto) {
        // validarProducto(nuevoProducto);
        return repository.save(productoActualizado(id, nuevoProducto));
    }

    // Eliminar producto existente por Id
    public void eliminarProducto(int id) {
        repository.delete(getProductoPorId(id));
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return repository.findByNombreContaining(nombre);
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        return repository.buscarPorCategoria(categoria);
    }

    /* Obtener productos por rango de precio */
    public List<Producto> listarPorRangoPrecio(double min, double max) {
        if (min < 0 || max < min) {
            throw new IllegalArgumentException("Los rangos de precio ingresados no son válidos");
        }
        return repository.findByPrecioBetween(min, max);
    }

    /* Alerta de stock bajo */
    public List<Producto> listarStockBajo(int stockLimite) {
        if (stockLimite < 0) {
            throw new IllegalArgumentException("El límite de stock no puede ser negativo");
        }
        return repository.findByStockLessThan(stockLimite);
    }

    /* Buscar por marca ignorando mayúsculas/minúsculas */
    public List<Producto> listarPorMarca(String marca) {
        if (marca == null || marca.isBlank()) {
            throw new IllegalArgumentException("La marca no puede estar vacía");
        }
        return repository.findByMarcaIgnoreCase(marca);
    }

    /* Obtener los productos más caros */
    public List<Producto> obtenerProductosMasCaros() {
        return repository.obtenerMasCaros();
    }

    private Producto productoActualizado(int id, Producto producto) {
        Producto p = getProductoPorId(id);
        p.setNombre(producto.getNombre());
        p.setPrecio(producto.getPrecio());
        p.setStock(producto.getStock());
        p.setCategoria(producto.getCategoria());
        p.setMarca(producto.getMarca());
        return p;
    }

    // private void validarProducto(Producto p) {
    // if (p.getNombre() == null || p.getNombre().isBlank()) {
    // throw new IllegalArgumentException("El nombre del producto no puede estar
    // vacio");
    // }
    // if (p.getPrecio() <= 0) {
    // throw new PrecioInvalidoException("El precio debe ser mayor a cero. Se
    // recibio: " + p.getPrecio());
    // }
    // if (p.getStock() < 0) {
    // throw new StockInsuficienteException("El stock no puede ser negativo. Se
    // recibio " + p.getStock());
    // }
    // }

}
