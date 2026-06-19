package com.techlab.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.techlab.ecommerce.exception.CategoriaNoEncontradaException;
import com.techlab.ecommerce.exception.CategoriaNombreInvalidoException;
import com.techlab.ecommerce.model.Categoria;

@Service
public class CategoriaService {
    private List<Categoria> listaCtg = new ArrayList<>();
    private static int contadorId = 1;

    public Categoria guardarCtg(Categoria ctg){
        validarCtg(ctg);
        ctg.setId(contadorId);
        listaCtg.add(ctg);
        contadorId++;
        return ctg;
    }

    public List<Categoria> listarCategorias(){
        return listaCtg;
    }

    public Categoria obtenerPorId(int id){
        for(Categoria ctg : listaCtg){
            if(ctg.getId() == id){
                return ctg;
            }
        }
        throw new CategoriaNoEncontradaException("No se encontro la categoría con id: " + id);
    }

    public Categoria actualizarCtg(int id, Categoria ctg){
        validarCtg(ctg);
        Categoria c = obtenerPorId(id);
        c.setNombre(ctg.getNombre());
        c.setDescripcion(ctg.getDescripcion());
        return c;
    }

    public void eliminar(int id){
        listaCtg.remove(obtenerPorId(id));
    }

    private void validarCtg(Categoria ctg){
        if(ctg.getNombre() == null || ctg.getNombre().isBlank()){
            throw new CategoriaNombreInvalidoException("El nombre de la categoria no puede estar vacio");
        }
    }
}
