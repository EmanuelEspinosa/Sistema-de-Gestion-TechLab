package com.techlab.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El nombre del producto no puede estar vacio")
    @Column(name = "nombre", nullable= false, length = 100)
    private String nombre;

    @Positive(message = "El precio debe ser mayor que cero")
    @Column(name = "precio", nullable = false)
    private double precio;

    @PositiveOrZero(message = "El stock no puede ser negativo")
    @Column(name = "stock", nullable = false)
    private int stock;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @NotBlank(message = "La marca del producto no puede estar vacia")
    @Column(name = "marca", length = 50)
    private String marca;

    @Column(name = "descripcion", length = 700)
    private String descripcion;

    @Column(name = "url_imagen", length = 700)
    private String urlImagen;

    public Producto(String nombre, double precio, int stock, Categoria categoria, String descripcion,String marca, String urlImagen){
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.marca = marca;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
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

    public Categoria getCategoria() {return categoria;}
    public void setCategoria(Categoria categoria) {this.categoria = categoria;}

    public String getMarca() {return marca;}
    public void setMarca(String marca) {this.marca = marca;}

    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public String getUrlImagen() {return urlImagen;}
    public void setUrlImagen(String urlImagen) {this.urlImagen = urlImagen;}

    
    @Override
    public String toString() {
        String nombreCtg = (categoria != null) ? categoria.getNombre() : "Sin categoria";
        return String.format("ID: %d | %-15s | $%10.2f | Stock: %5d | Categoría: %-10s | Marca: %-15s", id, nombre, precio, stock, nombreCtg, marca);
    }
}
