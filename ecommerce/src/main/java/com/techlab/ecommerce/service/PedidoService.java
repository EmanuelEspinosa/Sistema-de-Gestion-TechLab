package com.techlab.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techlab.ecommerce.exception.ProductoNoEncontradoException;
import com.techlab.ecommerce.exception.StockInsuficienteException;
import com.techlab.ecommerce.model.LineaPedido;
import com.techlab.ecommerce.model.Pedido;
import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.repository.PedidoRepository;
import com.techlab.ecommerce.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional
    public Pedido crearPedido(Pedido pedidoData) {
        double totalGeneral = 0;

        // Recorremos la lista de líneas que nos envió el cliente
        for (LineaPedido linea : pedidoData.getLineas()) {

            // 1. Busco el producto real en la BD para verificar precio y stock
            // actualizado
            Producto productoBD = productoRepository.findById(linea.getProducto().getId())
                    .orElseThrow(() -> new ProductoNoEncontradoException(
                            "No se puede registrar el pedido: El producto con ID " + linea.getProducto().getId()
                                    + " no existe."));

            // 2. Valido el Stock
            if (productoBD.getStock() < linea.getCantidad()) {
                throw new StockInsuficienteException(String.format(
                        "Stock insuficiente para el producto '%s'. Stock disponible: %d, solicitado: %d",
                        productoBD.getNombre(), productoBD.getStock(), linea.getCantidad()));
            }

            // 3. Disminuir el stock del producto
            productoBD.setStock(productoBD.getStock() - linea.getCantidad());
            productoRepository.save(productoBD); // Actualizo el stock en la tabla de productos

            // 4. Calculo el subtotal de la línea y acumulo al costo total
            linea.setProducto(productoBD); // Asocio el producto completo de la BD
            linea.setSubtotal(productoBD.getPrecio() * linea.getCantidad());
            totalGeneral += linea.getSubtotal();
        }
        // 5. Seteo el total calculado al pedido y lo guardo definitivamente
        pedidoData.setTotal(totalGeneral);
        return pedidoRepository.save(pedidoData);
    }

    public List<Pedido> obtenerTodos() {
        return pedidoRepository.findAll();
    }

    @Transactional
    public void eliminarPedido(int id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));

        for (LineaPedido linea : pedido.getLineas()) {
            Producto producto = linea.getProducto();
            // Le sumo al stock actual la cantidad que se había comprado
            producto.setStock(producto.getStock() + linea.getCantidad());
            // Guardo el producto actualizado
            productoRepository.save(producto);
        }

        // 3. Ahora que el stock volvió a sus productos, elimino el pedido de la BD
        pedidoRepository.delete(pedido);
    }
}
