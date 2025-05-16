package servicios;

import modelo.Producto;
import excepciones.ProductoNoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class GestorProductos {

    private List<Producto> productos;

    // Constructor
    public GestorProductos() {
        productos = new ArrayList<>();
        cargarProductosIniciales();  // Carga los productos por defecto
    }

    // Método para cargar los productos iniciales
    private void cargarProductosIniciales() {
        productos.add(new Producto("Monitor LED 24\"", 1200.00, 15));
        productos.add(new Producto("Teclado mecanico RGB", 450.00, 25));
        productos.add(new Producto("Mouse inalambrico", 300.00, 30));
        productos.add(new Producto("Memoria RAM 8GB DDR4", 350.00, 20));
        productos.add(new Producto("Disco SSD 500GB", 700.00, 18));
        productos.add(new Producto("Placa madre ATX", 950.00, 12));
        productos.add(new Producto("Procesador Intel i5", 1300.00, 10));
        productos.add(new Producto("Fuente de poder 600W", 600.00, 14));
        productos.add(new Producto("Gabinete con RGB", 800.00, 10));
        productos.add(new Producto("Cooler CPU", 250.00, 22));
        productos.add(new Producto("Disco duro 1TB", 500.00, 16));
        productos.add(new Producto("Webcam HD", 200.00, 30));
        productos.add(new Producto("Parlantes estereo", 150.00, 28));
        productos.add(new Producto("Impresora multifuncion", 900.00, 8));
        productos.add(new Producto("Router Wi-Fi", 350.00, 20));
        productos.add(new Producto("Adaptador USB Wi-Fi", 180.00, 35));
        productos.add(new Producto("Cable HDMI 2m", 50.00, 50));
        productos.add(new Producto("Mouse pad gamer", 120.00, 40));
        productos.add(new Producto("Hub USB 4 puertos", 100.00, 25));
        productos.add(new Producto("Limpieza para PC (kit)", 180.00, 12));
    }

    // Métodos para Agregar,Listar, Buscar por ID, Eliminar y Modificar productos.
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public List<Producto> listarProductos() {
        return this.productos;
    }

    public Producto buscarPorId(int id) throws ProductoNoEncontradoException {
        // Implementación de búsqueda por ID
       
        return productos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductoNoEncontradoException("Producto con ID " + id + " no encontrado."));
    }

    public void eliminarProducto(int id) {
        Producto producto = productos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (producto != null) {
            productos.remove(producto);
          //  System.out.println("Producto eliminado .");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public void modificarProducto(int id, String nuevoNombre, double nuevoPrecio, int nuevoStock) throws ProductoNoEncontradoException {
        Producto producto = buscarPorId(id);
        if (producto != null) {
            producto.setNombre(nuevoNombre);
            producto.setPrecio(nuevoPrecio);
            producto.setStock(nuevoStock);
            System.out.println("Producto modificado.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    // Métodos adicionales para filtrar por nombre o precio.
    public List<Producto> filtrarPorNombre(String nombre) {
        return productos.stream()
                .filter(p -> p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .toList();
    }

    public List<Producto> filtrarPorPrecio(double precioMin, double precioMax) {
        return productos.stream()
                .filter(p -> p.getPrecio() >= precioMin && p.getPrecio() <= precioMax)
                .toList();
    }

}
