package com.krakedev.productos.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.krakedev.productos.entidades.Producto;

@Service
public class ServicioProducto {

    private ArrayList<Producto> listaProductos;

    public ServicioProducto() {
        this.listaProductos = new ArrayList<>();
    }

    // 1. crear(Producto producto)
    public Producto crear(Producto producto) {
        for (Producto p : listaProductos) {
            if (p.getCodigo() == producto.getCodigo()) {
                return null;
            }
        }
        listaProductos.add(producto);
        return producto;
    }

    // 2. listar()
    public List<Producto> listar() {
        return listaProductos;
    }

    // 3. buscarPorCodigo(int codigo)
    public Producto buscarPorCodigo(int codigo) {
        for (Producto p : listaProductos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    // 4. actualizar(int codigo, Producto productoActualizado)
    public Producto actualizar(int codigo, Producto productoActualizado) {
        for (Producto p : listaProductos) {
            if (p.getCodigo() == codigo) {
                p.setNombre(productoActualizado.getNombre());
                p.setPrecio(productoActualizado.getPrecio());
                p.setStock(productoActualizado.getStock());
                return p;
            }
        }
        return null;
    }

    // 5. eliminar(int codigo)
    public boolean eliminar(int codigo) {
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getCodigo() == codigo) {
                listaProductos.remove(i);
                return true;
            }
        }
        return false;
    }
}