package com.techlab.ecommerce.model;
import java.util.ArrayList;
import java.util.List;
import com.techlab.ecommerce.util.ItemPedido;

public class Pedido {
    private int id;
    private List<ItemPedido> items = new ArrayList<>();

    public int getId() { return id;}
    public void setId(int id) {this.id = id;}
    public List<ItemPedido> getItems() {return items;}

    public double getTotalAPagar(){
        double totalAPagar = 0;
        for(ItemPedido item : items){
            totalAPagar+=(item.getProducto().getPrecio())*item.getCantidad();
        }
        return totalAPagar;
    }

    @Override
    public String toString() {

        String texto = "\n*****   (ID) PEDIDO N°" + id + "   *****\n";

        for(ItemPedido item : items){
            texto += item + "\n";
        }
        texto+="Total a pagar $" + getTotalAPagar();
        return texto;
    }
}
