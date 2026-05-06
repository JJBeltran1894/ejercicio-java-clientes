package com.krakedev.productos.controller;


import org.springframework.web.bind.annotation.*;

import com.krakedev.productos.entidades.Producto;
import com.krakedev.productos.servicios.ServicioProducto;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	
	private ServicioProducto servicioProducto;
	
	public ProductoController(ServicioProducto servicioProducto) {
		this.servicioProducto=servicioProducto;
	}

	// POST "/productos" - crear producto
	@PostMapping
	public Producto crear(@RequestBody Producto producto) {
		return servicioProducto.crear(producto);
	}

	// GET "/productos" - listar productos
	@GetMapping
	public List<Producto> listar() {
		return servicioProducto.listar();
	}

	// GET "/productos/{codigo}" - buscar por codigo
	@GetMapping("/{codigo}")
	public Producto buscarPorCodigo(@PathVariable int codigo) {
		return servicioProducto.buscarPorCodigo(codigo);
	}

	// PUT "/productos/{codigo}" - actualizar producto
	@PutMapping("/{codigo}")
	public Producto actualizar(@PathVariable int codigo, @RequestBody Producto producto) {
		return servicioProducto.actualizar(codigo, producto);
	}

	// DELETE "/productos/{codigo}" - eliminar producto
	@DeleteMapping("/{codigo}")
	public boolean eliminar(@PathVariable int codigo) {
		return servicioProducto.eliminar(codigo);
	}
}
