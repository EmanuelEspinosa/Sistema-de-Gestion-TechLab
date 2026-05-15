package com.techlab.ecommerce.service;
import java.util.ArrayList;
import java.util.List;
import com.techlab.ecommerce.exception.PedidoNoEncontradoException;
import com.techlab.ecommerce.model.Pedido;

public class PedidoService {
    private List<Pedido> pedidos = new ArrayList<>();
    private static int contadorId = 1;

    public Pedido guardarPedido(Pedido pedido){
        pedido.setId(contadorId);
        pedidos.add(pedido);
        contadorId++;
        return pedido;
    }

    public List<Pedido> listarPedidos(){
        return pedidos;
    }

    public Pedido getPedidoId(int id){
        for(Pedido p : pedidos){
            if(p.getId() == id){
                return p;
            }
        }
        throw new PedidoNoEncontradoException("\nPedido no encontrado\n");
    }

    public Pedido eliminarPedido(int id){
        Pedido order = getPedidoId(id);
        pedidos.remove(order);
        return order;
    }
}
