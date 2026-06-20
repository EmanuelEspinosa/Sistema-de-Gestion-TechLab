package com.techlab.ecommerce.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.techlab.ecommerce.exception.CategoriaNoEncontradaException;
// import com.techlab.ecommerce.exception.CategoriaNombreInvalidoException;
import com.techlab.ecommerce.model.Categoria;
import com.techlab.ecommerce.repository.CategoriaRepository;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository){
        this.repository = repository;
    }

    public Categoria guardarCtg(Categoria ctg){
        //validarCtg(ctg);
        return repository.save(ctg);
    }

    public List<Categoria> listarCategorias(){
        return repository.findAll();
    }

    public Categoria obtenerPorId(int id){
        return repository.findById(id).orElseThrow(() -> new CategoriaNoEncontradaException(String.format("No se encontro la categoria con Id %d", id)));
    }

    public Categoria actualizarCtg(int id, Categoria ctg){
        //validarCtg(ctg);
        return repository.save(categoriaActualizada(id, ctg));
    }

    public void eliminar(int id){
        repository.delete(obtenerPorId(id));
    }

    // private void validarCtg(Categoria ctg){
    //     if(ctg.getNombre() == null || ctg.getNombre().isBlank()){
    //         throw new CategoriaNombreInvalidoException("El nombre de la categoria no puede estar vacio");
    //     }
    // }

    private Categoria categoriaActualizada(int id, Categoria categ){
        Categoria c = obtenerPorId(id);
        c.setNombre(categ.getNombre());
        c.setDescripcion(categ.getDescripcion());
        return c;
    }
}
