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
        productos.add(new Producto("Monitor LED 27\"", 1600.00, 10));
        productos.add(new Producto("Teclado inalámbrico", 400.00, 18));
        productos.add(new Producto("Mouse gamer con DPI ajustable", 380.00, 20));
        productos.add(new Producto("Memoria RAM 16GB DDR4", 650.00, 15));
        productos.add(new Producto("Disco SSD 1TB", 1200.00, 10));
        productos.add(new Producto("Placa madre microATX", 850.00, 14));
        productos.add(new Producto("Procesador AMD Ryzen 5", 1250.00, 12));
        productos.add(new Producto("Fuente de poder 750W", 750.00, 8));
        productos.add(new Producto("Gabinete ATX con ventiladores", 950.00, 7));
        productos.add(new Producto("Cooler líquido AIO", 1100.00, 5));
        productos.add(new Producto("Disco duro externo 2TB", 1000.00, 9));
        productos.add(new Producto("Webcam 4K", 450.00, 12));
        productos.add(new Producto("Parlantes Bluetooth", 300.00, 20));
        productos.add(new Producto("Impresora laser", 1500.00, 6));
        productos.add(new Producto("Switch de red 8 puertos", 400.00, 10));
        productos.add(new Producto("Adaptador Bluetooth USB", 120.00, 25));
        productos.add(new Producto("Cable DisplayPort 1.5m", 70.00, 30));
        productos.add(new Producto("Soporte para monitor", 220.00, 15));
        productos.add(new Producto("Enfriador para laptop", 200.00, 18));
        productos.add(new Producto("Tira LED RGB para gabinete", 90.00, 25));
        productos.add(new Producto("Micrófono condensador", 700.00, 10));
        productos.add(new Producto("Auriculares gamer", 650.00, 17));
        productos.add(new Producto("Batería UPS 1000VA", 1700.00, 4));
        productos.add(new Producto("Tablet digitalizadora", 800.00, 6));
        productos.add(new Producto("Tarjeta gráfica NVIDIA GTX 1660", 2500.00, 5));
        productos.add(new Producto("Tarjeta de sonido externa", 300.00, 8));
        productos.add(new Producto("Controlador para juegos", 450.00, 13));
        productos.add(new Producto("Silla gamer ergonómica", 2200.00, 3));
        productos.add(new Producto("Licencia Windows 10 Pro", 600.00, 30));
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
