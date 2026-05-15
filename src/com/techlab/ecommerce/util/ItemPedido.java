package com.techlab.ecommerce.util;
import com.techlab.ecommerce.model.Producto;

public class ItemPedido {
    private Producto producto;
    private int cantidad;

    public ItemPedido(Producto producto, int cantidad){
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return String.format("Producto: %s | Cantidad: %d | Precio Unitario: %.2f", producto.getNombre(), cantidad, producto.getPrecio());
    }
}
