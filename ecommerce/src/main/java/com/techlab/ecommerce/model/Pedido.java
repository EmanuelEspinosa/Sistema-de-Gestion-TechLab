package com.techlab.ecommerce.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fecha_alta", nullable = false)
    private LocalDateTime fechaAlta = LocalDateTime.now();

    @Column(nullable = false)
    private Double total;

    // CascadeType.ALL guarda automáticamente las líneas en la BD al guardar el
    // Pedido.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id") 
    private List<LineaPedido> lineas = new ArrayList<>();

    public Pedido() {
    }

    // --- GETTERS Y SETTERS ---
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public LocalDateTime getFechaAlta() {return fechaAlta;}
    public void setFechaAlta(LocalDateTime fechaAlta) {this.fechaAlta = fechaAlta;}

    public Double getTotal() {return total;}
    public void setTotal(Double total) {this.total = total;}

    public List<LineaPedido> getLineas() {return lineas;}
    public void setLineas(List<LineaPedido> lineas) {this.lineas = lineas;}
}
