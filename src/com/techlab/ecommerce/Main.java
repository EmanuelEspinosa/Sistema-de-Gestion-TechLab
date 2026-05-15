package com.techlab.ecommerce;

import java.util.Scanner;

import com.techlab.ecommerce.exception.PedidoNoEncontradoException;
import com.techlab.ecommerce.exception.ProductoNoEncontradoException;
import com.techlab.ecommerce.exception.StockInsuficienteException;
import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.service.PedidoService;
import com.techlab.ecommerce.service.ProductoService;
import com.techlab.ecommerce.ui.MenuGestionPedidos;
import com.techlab.ecommerce.ui.MenuPrincipal;
import com.techlab.ecommerce.util.Validador;

public class Main {
    public static void main(String[] args) throws Exception {
        ProductoService serviceProd = new ProductoService();
        PedidoService servicePedido = new PedidoService();
        Scanner sc = new Scanner(System.in);
        MenuPrincipal menu = new MenuPrincipal(sc, serviceProd);
        MenuGestionPedidos menuPedidos = new MenuGestionPedidos(sc, serviceProd, servicePedido);
        cargarProductosTest(serviceProd);
        int optMenuPrincipal;
        int optMenuPedidos;
        do {
            menu.showMainMenu();
            optMenuPrincipal = Validador.readInteger(sc, "Elija una opcion: ");
            try{
                switch (optMenuPrincipal) {
                    case 1:
                        menu.agregarProducto();
                        break;
                    case 2:
                        menu.listarProductos();
                        break;
                    case 3:
                        menu.buscarProducto();
                        break;
                    case 4:
                        menu.actualizarProducto();
                        break;
                    case 5:
                        menu.eliminarProducto();
                        break;
                    case 6:
                        do {
                            menuPedidos.showMenuOrders();
                            optMenuPedidos = Validador.readInteger(sc, "Elija una opcion: ");
                            try{
                                switch (optMenuPedidos) {
                                    case 1:
                                        menuPedidos.crearPedido();
                                        break;
                                    case 2:
                                        menuPedidos.listarPedidos();
                                        break;
                                    case 3:
                                        menuPedidos.verPedido();
                                        break;
                                    case 4:
                                        menuPedidos.eliminarPedido();
                                        break;
                                    case 0:
                                        System.out.println("Volviendo al menú principal...\n");
                                        break;
                                    default:
                                        System.out.println("Ingreso una opción invalida. Intentelo nuevamente");
                                        break;
                                }
                            }catch (PedidoNoEncontradoException | StockInsuficienteException e){
                                System.out.println(e.getMessage());
                            }                            
                        } while (optMenuPedidos != 0);
                        break;
                    case 0:
                        System.out.println("\n¡¡¡Muchas gracias por su visita!!!\n");
                        break;
                    default:
                        System.out.println("Ingreso una opción invalida. Intentelo nuevamente");
                        break;
                }
            }catch(ProductoNoEncontradoException e){
                System.out.println(e.getMessage());
            }
        } while (optMenuPrincipal != 0);

        sc.close();
    }

    private static void cargarProductosTest(ProductoService prodService){
        prodService.guardarProducto(new Producto("Redmi Note 10", 250000, 5, "Celular", "Xiaomi"));
        prodService.guardarProducto(new Producto("Galaxy A16 4G", 399500,3, "Celular", "Samsung"));
        prodService.guardarProducto(new Producto("Jd Capri 1.83", 40500,3, "Smartwatch", "JD"));
        prodService.guardarProducto(new Producto("Gamer G435", 126000,10, "Auriculares", "Logitech G"));
        prodService.guardarProducto(new Producto("Ecotank L1250", 335699,2, "Impresora", "Epson"));
        prodService.guardarProducto(new Producto("Lenovo M11 8GB", 432899,5, "Tablet", "Lenovo"));
        prodService.guardarProducto(new Producto("Exo 14 Intel", 271999,3, "Notebook", "Exo"));
        prodService.guardarProducto(new Producto("Gamer 24 Master", 141300,7, "Monitor", "Cooler Master"));
        prodService.guardarProducto(new Producto("Gadnic N4020", 328799,7, "Notbook", "Gadnic"));
        prodService.guardarProducto(new Producto("Acer Aspire", 1439999,2, "Notbook", "Acer"));
    }
}
