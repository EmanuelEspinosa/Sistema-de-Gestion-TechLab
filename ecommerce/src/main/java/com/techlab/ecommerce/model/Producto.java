package com.techlab.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable= false, length = 100)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "categoria", length = 50)
    private String categoria;

    @Column(name = "marca", length = 50)
    private String marca;

    public Producto(String nombre, double precio, int stock, String categoria, String marca){
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.marca = marca;
    }

    public Producto(){}

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
