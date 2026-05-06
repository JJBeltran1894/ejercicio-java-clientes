package com.krakedev.clientes.test;

import org.junit.jupiter.api.Test;

import com.krakedev.clientes.entidades.Cliente;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

	@Test
    // Validar que el constructor vacío y los setters asignen correctamente los valores
    void testConstructorVacioYSetters() {
        Cliente cliente = new Cliente();
        cliente.setCedula("1700000000");
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");

        assertEquals("1700000000", cliente.getCedula());
        assertEquals("Juan", cliente.getNombre());
        // LÍNEA CORREGIDA:
        assertEquals("Perez", cliente.getApellido());
    }

    @Test
    // Validar que el constructor con parámetros asigne la información de manera inmediata
    void testConstructorParametrosYGetters() {
        Cliente cliente = new Cliente("1711111111", "Ana", "Gomez","agomez@gmail.com");

        assertEquals("1711111111", cliente.getCedula());
        assertEquals("Ana", cliente.getNombre());
        assertEquals("Gomez", cliente.getApellido());
    }

    @Test
    // Validar que el método toString concatene correctamente los atributos de la clase
    void testToString() {
        Cliente cliente = new Cliente("1722222222", "Carlos", "Ruiz","cruiz@gmail.com");
        String esperado = "Cliente [cedula=1722222222, nombre=Carlos, apellido=Ruiz]";
        
        assertEquals(esperado, cliente.toString());
    }
}