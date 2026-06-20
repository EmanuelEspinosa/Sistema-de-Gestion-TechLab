package com.techlab.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techlab.ecommerce.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByNombre(String nombre);

    @Query("SELECT p FROM Producto p WHERE p.categoria.nombre = :nombreCategoria")
    List<Producto> buscarPorCategoria(@Param("nombreCategoria") String nombreCategoria);

    /*Filtrar por rango de precios (Util para buscador del Ecommerce)*/
    List<Producto> findByPrecioBetween(double precioMin, double precioMax);

    /*Alerta de Stock Bajo (Util para administradores)*/
    List<Producto> findByStockLessThan(int limiteStock);

    /*Buscar por marca específica*/
    List<Producto> findByMarcaIgnoreCase(String marca);

    /*btener el/los producto más caro de todos*/
    @Query("SELECT p FROM Producto p WHERE p.precio = (SELECT MAX(prod.precio) FROM Producto prod)")
    List<Producto> obtenerMasCaros();

}
