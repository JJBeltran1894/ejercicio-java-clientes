package com.krakedev.productos.test;

import org.junit.jupiter.api.Test;

import com.krakedev.productos.entidades.Producto;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoTest {

    @Test
    // Validar constructores vacíos y el funcionamiento de los setters numéricos y de texto
    void testConstructorVacioYSetters() {
        Producto producto = new Producto();
        producto.setNombre("Monitor");
        producto.setCodigo(10);
        producto.setPrecio(150.75);
        producto.setStock(5);

        assertEquals("Monitor", producto.getNombre());
        assertEquals(10, producto.getCodigo());
        // Tolerancia de 0.001 para aserciones con variables de tipo double
        assertEquals(150.75, producto.getPrecio(), 0.001);
        assertEquals(5, producto.getStock());
    }

    @Test
    // Validar asignación de parámetros desde la instanciación
    void testConstructorParametrosYGetters() {
        Producto producto = new Producto("Teclado", 20, 45.99, 12);

        assertEquals("Teclado", producto.getNombre());
        assertEquals(20, producto.getCodigo());
        assertEquals(45.99, producto.getPrecio(), 0.001);
        assertEquals(12, producto.getStock());
    }

    @Test
    // Validar el formato de la cadena generada por toString
    void testToString() {
        Producto producto = new Producto("Mouse", 30, 25.50, 100);
        String esperado = "Producto{nombre='Mouse', codigo=30, precio=25.5, stock=100}";
        
        assertEquals(esperado, producto.toString());
    }
}