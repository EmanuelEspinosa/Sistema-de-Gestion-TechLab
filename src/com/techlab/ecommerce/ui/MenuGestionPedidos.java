package com.techlab.ecommerce.ui;
import java.util.Scanner;
import com.techlab.ecommerce.exception.StockInsuficienteException;
import com.techlab.ecommerce.model.Pedido;
import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.service.PedidoService;
import com.techlab.ecommerce.service.ProductoService;
import com.techlab.ecommerce.util.ItemPedido;
import com.techlab.ecommerce.util.Validador;

public class MenuGestionPedidos {
    private Scanner sc;
    private ProductoService productoService;
    private PedidoService pedidoService;

    public MenuGestionPedidos(Scanner sc, ProductoService productoService, PedidoService pedidoService){
        this.sc = sc;
        this.productoService = productoService;
        this.pedidoService = pedidoService;
    }

    public void showMenuOrders(){
        System.out.println("\n======================================");
        System.out.println("========= GESTIÓN DE PEDIDOS =========");
        System.out.println("======================================");
        System.out.println("1) Crear pedido");
        System.out.println("2) Listar pedidos");
        System.out.println("3) ver pedido");
        System.out.println("4) Eliminar pedido");
        System.out.println("0) Regresar al menu principal");
        System.out.println("======================================");
    }

    public void crearPedido(){
        Pedido pedido = new Pedido();
        int cantidadProductos = Validador.readInteger(sc, "Cuantos productos distintos desea agregar: ");


        for(int i = 0; i < cantidadProductos; i++){
            int idProducto = Validador.readInteger(sc, "Ingrese id del producto: ");
            Producto producto = productoService.getProductoPorId(idProducto);
            int cantidad = Validador.readInteger(sc, "Ingrese cantidad: ");

            if(producto.getStock() < cantidad){
                throw new StockInsuficienteException(String.format("Stock insuficiente. solo hay disponibles %d unidades", producto.getStock()));
            }
            else{
                producto.setStock(producto.getStock() - cantidad);
            }

            ItemPedido item = new ItemPedido(producto, cantidad);
            pedido.getItems().add(item);
        }
        pedidoService.guardarPedido(pedido);
        System.out.println("Pedido creado correctamente\n" + pedido);
    }

    public void listarPedidos(){
        if(pedidoService.listarPedidos().size() == 0){
            System.out.println("\n--- No hay pedidos cargados en el sistema ---\n");
        }else{
            for(Pedido p : pedidoService.listarPedidos()){
                System.out.println(p);
            }
        }
    }

    public void verPedido(){
        int id = Validador.readInteger(sc, "Ingrese el Id del pedido: ");
        System.out.println(pedidoService.getPedidoId(id));
    }

    public void eliminarPedido(){
        int id = Validador.readInteger(sc, "Ingrese el Id del pedido que desea eliminar: ");
        Pedido pedidoEliminado = pedidoService.eliminarPedido(id);
        for(ItemPedido item : pedidoEliminado.getItems()){
            item.getProducto().setStock(item.getProducto().getStock() + item.getCantidad());
        }
        System.out.println(String.format("El pedido fue eliminado con éxito.\nPedido eliminado: \n%s", pedidoEliminado));
    }
}
