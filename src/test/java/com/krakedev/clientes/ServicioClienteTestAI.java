package com.krakedev.clientes;

import com.krakedev.clientes.entidades.Cliente;
import com.krakedev.clientes.servicios.ServicioCliente;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ServicioClienteTestAI {

    // ==========================================
    // PRUEBAS DE CREACIÓN
    // ==========================================

    @Test
    public void testCrearClienteExito() {
        // Validación: Verifica que un cliente nuevo se agregue correctamente a la lista.
        ServicioCliente servicio = new ServicioCliente();
        Cliente nuevoCliente = new Cliente("1717171717", "Juan", "Perez");

        Cliente resultado = servicio.crear(nuevoCliente);

        assertNotNull(resultado, "El método debe retornar el cliente creado.");
        assertEquals("1717171717", resultado.getCedula(), "La cédula debe coincidir.");
        assertEquals(1, servicio.listar().size(), "La lista debe contener 1 cliente.");
    }

    @Test
    public void testCrearClienteDuplicado() {
        // Validación: Verifica que no se pueda crear un cliente si la cédula ya existe.
        ServicioCliente servicio = new ServicioCliente();
        Cliente cliente1 = new Cliente("1717171717", "Juan", "Perez");
        Cliente cliente2 = new Cliente("1717171717", "Carlos", "Lopez"); // Misma cédula

        servicio.crear(cliente1);
        Cliente resultado = servicio.crear(cliente2); // Intento de duplicado

        assertNull(resultado, "El método debe retornar null si la cédula ya existe.");
        assertEquals(1, servicio.listar().size(), "La lista debe mantenerse con 1 solo cliente.");
    }

    // ==========================================
    // PRUEBAS DE BÚSQUEDA Y LISTADO
    // ==========================================

    @Test
    public void testBuscarPorCedulaEncontrado() {
        // Validación: Verifica que se recupere correctamente un cliente que sí existe.
        ServicioCliente servicio = new ServicioCliente();
        servicio.crear(new Cliente("1818181818", "Ana", "Gomez"));

        Cliente encontrado = servicio.buscarPorCedula("1818181818");

        assertNotNull(encontrado, "El cliente debe ser encontrado.");
        assertEquals("Ana", encontrado.getNombre(), "El nombre debe coincidir con el cliente guardado.");
    }

    @Test
    public void testBuscarPorCedulaNoEncontrado() {
        // Validación: Verifica que retorne null al buscar una cédula inexistente.
        ServicioCliente servicio = new ServicioCliente();
        servicio.crear(new Cliente("1818181818", "Ana", "Gomez"));

        Cliente encontrado = servicio.buscarPorCedula("0000000000");

        assertNull(encontrado, "Debe retornar null si la cédula no existe en la lista.");
    }

    @Test
    public void testListarClientes() {
        // Validación: Verifica que el método devuelva la lista completa de clientes.
        ServicioCliente servicio = new ServicioCliente();
        servicio.crear(new Cliente("111", "A", "B"));
        servicio.crear(new Cliente("222", "C", "D"));

        List<Cliente> lista = servicio.listar();

        assertEquals(2, lista.size(), "Debe retornar una lista con 2 clientes.");
    }

    // ==========================================
    // PRUEBAS DE ACTUALIZACIÓN
    // ==========================================

    @Test
    public void testActualizarClienteExito() {
        // Validación: Verifica que se actualicen los datos de un cliente existente.
        ServicioCliente servicio = new ServicioCliente();
        servicio.crear(new Cliente("12345", "Luis", "Ruiz"));

        Cliente datosNuevos = new Cliente("12345", "Luis Mario", "Ruiz Update");
        Cliente resultado = servicio.actualizar("12345", datosNuevos);

        assertNotNull(resultado, "Debe retornar el cliente actualizado.");
        assertEquals("Luis Mario", resultado.getNombre(), "El nombre debió actualizarse.");
        assertEquals("Ruiz Update", resultado.getApellido(), "El apellido debió actualizarse.");
    }

    @Test
    public void testActualizarClienteInexistente() {
        // Validación: Verifica que retorne null al intentar actualizar un cliente que no existe.
        ServicioCliente servicio = new ServicioCliente();
        Cliente datosNuevos = new Cliente("999", "Fantasma", "No Existe");

        Cliente resultado = servicio.actualizar("999", datosNuevos);

        assertNull(resultado, "Debe retornar null si el cliente a actualizar no existe.");
    }

    // ==========================================
    // PRUEBAS DE ELIMINACIÓN
    // ==========================================

    @Test
    public void testEliminarClienteExito() {
        // Validación: Verifica que un cliente existente sea removido de la lista.
        ServicioCliente servicio = new ServicioCliente();
        servicio.crear(new Cliente("555", "Pedro", "Perez"));

        boolean resultado = servicio.eliminar("555");

        assertTrue(resultado, "Debe retornar true al eliminar exitosamente.");
        assertEquals(0, servicio.listar().size(), "La lista debe quedar vacía.");
    }

    @Test
    public void testEliminarClienteInexistente() {
        // Validación: Verifica que retorne false al intentar eliminar una cédula que no está registrada.
        ServicioCliente servicio = new ServicioCliente();
        servicio.crear(new Cliente("555", "Pedro", "Perez"));

        boolean resultado = servicio.eliminar("444"); // Cédula que no existe

        assertFalse(resultado, "Debe retornar false si el cliente a eliminar no existe.");
        assertEquals(1, servicio.listar().size(), "El tamaño de la lista no debe verse afectado.");
    }
}
