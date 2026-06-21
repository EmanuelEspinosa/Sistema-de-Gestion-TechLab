package com.techlab.ecommerce.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CategoriaNoEncontradaException.class)
    public ResponseEntity<String> handleCategoriaNoEncontrada(CategoriaNoEncontradaException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(CategoriaNombreInvalidoException.class)
    public ResponseEntity<String> handleCategoriaNombreInvalido(CategoriaNombreInvalidoException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<String> handleProductoNoEncontrado(ProductoNoEncontradoException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidaciones(MethodArgumentNotValidException e) {
        Map<String, String> errores = new HashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(StockInsuficienteException.class)
    public ResponseEntity<String> handleStockInsuficiente(StockInsuficienteException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
