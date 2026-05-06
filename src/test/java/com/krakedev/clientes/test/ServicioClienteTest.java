package com.krakedev.clientes.test;

import com.krakedev.clientes.entidades.Cliente;
import com.krakedev.clientes.servicios.ServicioCliente;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ServicioClienteTest {

    private ServicioCliente servicio;

    @BeforeEach
    void setUp() {
        servicio = new ServicioCliente();
    }

    @Test
    // Camino Feliz: Validar que un cliente nuevo se agregue correctamente a la lista (entra al else)
    void testCrearClienteNuevoExito() {
        Cliente nuevo = new Cliente("1234567890", "Luis", "Mena","lmena@gmail.com");
        Cliente resultado = servicio.crear(nuevo);

        assertNotNull(resultado);
        assertEquals("1234567890", resultado.getCedula());
        assertEquals(1, servicio.listar().size());
    }

    @Test
    // Escenario de Fallo: Validar que no se cree un cliente si la cédula ya existe (entra al if)
    void testCrearClienteDuplicadoFallo() {
        servicio.crear(new Cliente("1234567890", "Luis", "Mena", "lmena@gmail.com"));
        
        Cliente duplicado = new Cliente("1234567890", "Otro", "Nombre","onombre@gmail.com");
        Cliente resultado = servicio.crear(duplicado);

        assertNull(resultado);
        assertEquals(1, servicio.listar().size()); // La lista no debió crecer
    }

    @Test
    // Camino Feliz: Validar que se encuentre un cliente existente por su cédula
    void testBuscarPorCedulaExito() {
        servicio.crear(new Cliente("0987654321", "Maria", "Paz","mpaz@gmail.com"));
        Cliente encontrado = servicio.buscarPorCedula("0987654321");

        assertNotNull(encontrado);
        assertEquals("Maria", encontrado.getNombre());
    }

    @Test
    // Escenario de Fallo: Validar que retorne null al buscar una cédula inexistente
    void testBuscarPorCedulaNoEncontrado() {
        Cliente encontrado = servicio.buscarPorCedula("9999999999");
        assertNull(encontrado);
    }

    @Test
    // Camino Feliz: Validar que listar retorne todos los elementos almacenados
    void testListar() {
        servicio.crear(new Cliente("111", "A", "B","ab@gmail.com"));
        servicio.crear(new Cliente("222", "C", "D","cd@gmail.com"));
        
        List<Cliente> lista = servicio.listar();
        assertEquals(2, lista.size());
    }

    @Test
    // Camino Feliz: Validar que se actualicen los datos de un cliente existente (entra al if)
    void testActualizarClienteExito() {
        servicio.crear(new Cliente("555", "Viejo", "Nombre","vnombre@gmail.com"));
        
        Cliente datosNuevos = new Cliente("555", "Nuevo", "Apellido","nuevo@gmail.com");
        Cliente resultado = servicio.actualizar("555", datosNuevos);

        assertNotNull(resultado);
        assertEquals("Nuevo", resultado.getNombre());
        assertEquals("Apellido", resultado.getApellido());
    }

    @Test
    // Escenario de Fallo: Validar que retorne null si se intenta actualizar un cliente que no existe
    void testActualizarClienteNoExistente() {
        Cliente datosNuevos = new Cliente("777", "Fantasma", "Gomez","fgomez@gmail.com");
        Cliente resultado = servicio.actualizar("777", datosNuevos);

        assertNull(resultado);
    }

    @Test
    // Camino Feliz: Validar que se elimine correctamente un cliente existente (entra al if)
    void testEliminarClienteExito() {
        servicio.crear(new Cliente("888", "Para", "Borrar","no@correo.com"));
        boolean eliminado = servicio.eliminar("888");

        assertTrue(eliminado);
        assertEquals(0, servicio.listar().size());
    }

    @Test
    // Escenario de Fallo: Validar que retorne false si se intenta eliminar una cédula que no existe (entra al else)
    void testEliminarClienteNoExistenteFallo() {
        boolean eliminado = servicio.eliminar("000");
        assertFalse(eliminado);
    }
}