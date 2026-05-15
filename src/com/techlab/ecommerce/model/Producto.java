package com.techlab.ecommerce.model;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;
    private String marca;

    public Producto(String nombre, double precio, int stock, String categoria, String marca){
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.marca = marca;
    }

    //Propiedades de acceso
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public double getPrecio() {return precio;}
    public void setPrecio(double precio) {this.precio = precio;}

    public int getStock() {return stock;}
    public void setStock(int stock) {this.stock = stock;}

    public String getCategoria() {return categoria;}
    public void setCategoria(String categoria) {this.categoria = categoria;}

    public String getMarca() {return marca;}
    public void setMarca(String marca) {this.marca = marca;}

    @Override
    public String toString() {
        return String.format("ID: %d | %-15s | $%10.2f | Stock: %5d | Categoría: %-10s | Marca: %-15s", id, nombre, precio, stock, categoria, marca);
    }
}
