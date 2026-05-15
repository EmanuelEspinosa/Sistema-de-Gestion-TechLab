package com.techlab.ecommerce.ui;
import java.util.List;
import java.util.Scanner;
import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.service.ProductoService;
import com.techlab.ecommerce.util.Validador;

public class MenuPrincipal {
    private final Scanner sc;
    private final ProductoService servicioProducto;

    public MenuPrincipal(Scanner sc, ProductoService servicioProducto){
        this.sc = sc;
        this.servicioProducto = servicioProducto;
    }

    public void showMainMenu(){
        System.out.println("======================================");
        System.out.println("==== SISTEMA DE GESTIÓN - TECHLAB ====");
        System.out.println("======================================");
        System.out.println("1) Agregar producto");
        System.out.println("2) Listar productos");
        System.out.println("3) Buscar producto por Id");
        System.out.println("4) Actualizar productos");
        System.out.println("5) Eliminar producto");
        System.out.println("6) Crear pedido");
        System.out.println("0) Salir");
        System.out.println("======================================");
    }

    public void agregarProducto(){
        System.out.println("==== AGREGAR NUEVO PRODUCTO ====");

        String nombre = Validador.readString(sc, "Nombre: ");;
        String marca = Validador.readString(sc, "Marca: ");
        double precio = Validador.readDouble(sc, "Precio: ");
        String categoria = Validador.readString(sc, "Categoria: ");
        int stock = Validador.readInteger(sc, "Stock: ");

        Producto p = new Producto(nombre, precio, stock, categoria, marca);
        Producto productoGuardado = servicioProducto.guardarProducto(p);
        System.out.println(String.format("Producto con id %d agregado satisfactoriamente", productoGuardado.getId()));
    }

    public void listarProductos(){
        List<Producto> listP = servicioProducto.getListaProductos();
        if(listP.isEmpty()){
            System.out.println("No hay productos cargados");
            return;
        }
        System.out.println("----- Catalogo -----");
        for(Producto p : listP){
            System.out.println(p);
        }
    }

    public void buscarProducto(){
        int id = Validador.readInteger(sc, "Ingrese el Id del producto: ");
        Producto p = servicioProducto.getProductoPorId(id);
        System.out.println("Producto encontrado: " + p);
    }

    public void actualizarProducto(){
        int id = Validador.readInteger(sc, "Ingrese el Id del producto a actualizar: ");
        System.out.println("Datos actuales:");
        System.out.println(servicioProducto.getProductoPorId(id));

        System.out.println("--- Ingrese los nuevos datos ---");
        String nuevoNombre = Validador.readString(sc, "Ingrese nuevo nombre: ");
        String nuevaMarca = Validador.readString(sc, "Ingrese nueva marca: ");
        int nuevoStock = Validador.readInteger(sc, "Ingrese nuevo valor de stock");
        double nuevoPrecio = Validador.readDouble(sc, "Ingrese nuevo precio del producto: ");
        String nuevaCategoria = Validador.readString(sc, "ingrese nueva categoria del producto");

        Producto productoActualizado = servicioProducto.actualizarProducto(id, new Producto(nuevoNombre, nuevoPrecio, nuevoStock, nuevaCategoria, nuevaMarca));
        System.out.println("Producto actualizado con éxito: " + productoActualizado);
    }

    public void eliminarProducto(){
        int id = Validador.readInteger(sc, "Ingrese el Id del producto a eliminar: ");
        servicioProducto.eliminarProducto(id);
        System.out.println("Producto eliminado");
    }
}
