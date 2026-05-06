package com.krakedev.productos.test;

import com.krakedev.productos.entidades.Producto;
import com.krakedev.productos.servicios.ServicioProducto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ServicioProductoTest {

    private ServicioProducto servicio;

    @BeforeEach
    void setUp() {
        servicio = new ServicioProducto();
    }

    @Test
    // Camino Feliz: El producto no existe en la lista, por lo que el for no retorna null y lo agrega.
    void testCrearProductoNuevoExito() {
        Producto nuevo = new Producto("Lapiz", 1, 0.50, 100);
        Producto resultado = servicio.crear(nuevo);

        assertNotNull(resultado);
        assertEquals(1, resultado.getCodigo());
        assertEquals(1, servicio.listar().size());
    }

    @Test
    // Escenario de Fallo: El producto ya existe, el for entra al if y retorna null abortando la creación.
    void testCrearProductoExistenteFallo() {
        servicio.crear(new Producto("Lapiz", 1, 0.50, 100));
        
        Producto repetido = new Producto("Esfero", 1, 0.80, 50);
        Producto resultado = servicio.crear(repetido);

        assertNull(resultado);
        assertEquals(1, servicio.listar().size()); // Valida que no se insertó
    }

    @Test
    // Camino Feliz: Valida que devuelva la colección exacta de los elementos ingresados
    void testListar() {
        servicio.crear(new Producto("A", 100, 1.0, 10));
        servicio.crear(new Producto("B", 200, 2.0, 20));
        
        List<Producto> lista = servicio.listar();
        assertEquals(2, lista.size());
    }

    @Test
    // Camino Feliz: Encuentra el producto iterando la lista (entra al if dentro del for)
    void testBuscarPorCodigoExito() {
        servicio.crear(new Producto("Borrador", 5, 0.25, 30));
        Producto encontrado = servicio.buscarPorCodigo(5);

        assertNotNull(encontrado);
        assertEquals("Borrador", encontrado.getNombre());
    }

    @Test
    // Escenario de Fallo: Recorre toda la lista sin entrar al if, terminando en un return null
    void testBuscarPorCodigoNoEncontrado() {
        Producto encontrado = servicio.buscarPorCodigo(99);
        assertNull(encontrado);
    }

    @Test
    // Camino Feliz: Encuentra el código, actualiza nombre, precio y stock, y retorna el objeto actualizado
    void testActualizarProductoExito() {
        servicio.crear(new Producto("Cuaderno", 10, 2.00, 15));
        
        Producto actualizacion = new Producto("Cuaderno Universitario", 10, 2.50, 25);
        Producto resultado = servicio.actualizar(10, actualizacion);

        assertNotNull(resultado);
        assertEquals("Cuaderno Universitario", resultado.getNombre());
        assertEquals(2.50, resultado.getPrecio(), 0.001);
        assertEquals(25, resultado.getStock());
    }

    @Test
    // Escenario de Fallo: El bucle no encuentra el código, por ende no entra al if y retorna null
    void testActualizarProductoNoExistente() {
        Producto actualizacion = new Producto("Marcador", 404, 1.50, 10);
        Producto resultado = servicio.actualizar(404, actualizacion);

        assertNull(resultado);
    }

    @Test
    // Camino Feliz: Encuentra el elemento por índice, lo remueve de la lista y retorna true
    void testEliminarProductoExito() {
        servicio.crear(new Producto("Regla", 7, 1.00, 40));
        boolean eliminado = servicio.eliminar(7);

        assertTrue(eliminado);
        assertEquals(0, servicio.listar().size());
    }

    @Test
    // Escenario de Fallo: Recorre toda la lista de tamaños iterables, no encuentra el if y retorna false
    void testEliminarProductoNoExistenteFallo() {
        boolean eliminado = servicio.eliminar(888);
        assertFalse(eliminado);
    }
}