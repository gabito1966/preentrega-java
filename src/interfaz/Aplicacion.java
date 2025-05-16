package interfaz;

import modelo.*;
import servicios.*;
import excepciones.*;
import java.util.List;

import java.util.Scanner;

public class Aplicacion {

    public static void main(String[] args) throws ProductoNoEncontradoException {
        GestorProductos gestorProductos = new GestorProductos();
        GestorPedidos gestorPedidos = new GestorPedidos();
        Scanner teclado = new Scanner(System.in);

        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- MENU---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Modificar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Filtrar productos por nombre o precio");
            System.out.println("6. Buscar productos por ID");
            System.out.println("7. Crear pedido");
            System.out.println("8. Listar pedidos");
            System.out.println("9. Buscar pedido por ID");
            System.out.println("10. Mostrar productos mas vendidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            System.out.println("");
            int opcion = teclado.nextInt();

            switch (opcion) {
                case 1 -> {
                    teclado.nextLine(); // Limpiar el buffer del scanner
                    System.out.print("Nombre del producto: ");
                    String nombre = teclado.nextLine();
                    System.out.print("Precio: ");
                    double precio = teclado.nextDouble();
                    System.out.println("Stock");
                    int stock = teclado.nextInt();
                    gestorProductos.agregarProducto(new Producto(nombre, precio, stock));
                    System.out.println("Nuevo producto agregado.");
                }

                case 2 -> {
                    List<Producto> productos = gestorProductos.listarProductos();
                    if (productos.isEmpty()) {
                        System.out.println("No hay productos cargados.");
                    } else {
                        productos.forEach(System.out::println);
                    }
                }

                case 3 -> {
                    System.out.print("Ingrese el ID del producto a modificar: ");
                    int idModificar = teclado.nextInt();
                    teclado.nextLine();  // Limpiar buffer
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = teclado.nextLine();
                    System.out.print("Nuevo precio: ");
                    double nuevoPrecio = teclado.nextDouble();
                    System.out.print("Nuevo stock: ");
                    int nuevoStock = teclado.nextInt();
                    gestorProductos.modificarProducto(idModificar, nuevoNombre, nuevoPrecio, nuevoStock);
                }

                case 4 -> {
                    System.out.print("Ingrese el ID del producto a eliminar: ");
                    int idEliminar = teclado.nextInt();
                    teclado.nextLine(); // Consumir el salto de línea pendiente

                    Producto producto = gestorProductos.buscarPorId(idEliminar);

                    if (producto != null) {
                        System.out.println("Producto encontrado: " + producto.getNombre());
                        System.out.println("Esta seguro que desea eliminar este producto: (s/n)");
                        String confirmacion = teclado.nextLine();

                        if (confirmacion.equalsIgnoreCase("s")) {
                            gestorProductos.eliminarProducto(idEliminar);
                            System.out.println("Producto eliminado correctamente.");
                        } else {
                            System.out.println("Eliminacion cancelada.");
                        }
                    } else {
                        System.out.println("No se encontro ningun producto con el Id ingresado");
                    }
                }
                case 5 -> {
                    System.out.println("1. Filtrar por nombre");
                    System.out.println("2. Filtrar por precio");
                    System.out.print("Seleccione una opcion: ");
                    int opcionFiltro = teclado.nextInt();
                    switch (opcionFiltro) {
                        case 1 -> {
                            teclado.nextLine();  // Limpiar buffer
                            System.out.print("Ingrese el nombre o parte del nombre a filtrar: ");
                            String nombreFiltro = teclado.nextLine();
                            gestorProductos.filtrarPorNombre(nombreFiltro).forEach(System.out::println);
                        }
                        case 2 -> {
                            System.out.print("Ingrese el precio minimo: ");
                            double precioMin = teclado.nextDouble();
                            System.out.print("Ingrese el precio maximo: ");
                            double precioMax = teclado.nextDouble();
                            gestorProductos.filtrarPorPrecio(precioMin, precioMax).forEach(System.out::println);
                        }
                        default ->
                            System.out.println("Opcion invalida.");
                    }
                }
                case 6 -> {
                    System.out.println("Ingrese el Id de producto a buscar");
                    int idProductoBuscar = teclado.nextInt();
                    try {
                        Producto productoEncontrado = gestorProductos.buscarPorId(idProductoBuscar);
                        System.out.println(productoEncontrado);
                    } catch (ProductoNoEncontradoException e) {
                        System.out.println(e.getMessage()); // Muestra "Producto con ID 30 no encontrado."
                    }
                }
                case 7 -> {
                    Pedido pedido = gestorPedidos.crearPedido();
                    boolean agregando = true;
                    while (agregando) {
                        try {
                            System.out.print("ID del producto: ");
                            int idProd = teclado.nextInt();
                            Producto prod = gestorProductos.buscarPorId(idProd);
                            System.out.print("Cantidad: ");
                            int cantidad = teclado.nextInt();
                            pedido.agregarItem(new ItemPedido(prod, cantidad));
                        } catch (ProductoNoEncontradoException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.print("¿Agregar otro producto? (s/n): ");
                        String continuar = teclado.next();
                        agregando = continuar.equalsIgnoreCase("s");
                    }
                    System.out.println("Pedido creado:\n" + pedido);
                }
                case 8 ->
                    gestorPedidos.listarPedidos().forEach(System.out::println);

                case 9 -> {
                    System.out.print("Ingrese el ID del pedido: ");
                    int idPedidoBuscar = teclado.nextInt();
                    Pedido pedidoEncontrado = gestorPedidos.buscarPedidoPorId(idPedidoBuscar);
                    if (pedidoEncontrado != null) {
                        System.out.println(pedidoEncontrado);
                    } else {
                        System.out.println("Pedido no encontrado.");
                    }
                }
                case 10 -> {
                    System.out.println("Productos más vendidos:");
                    gestorPedidos.productosMasVendidos().forEach(System.out::println);
                }

                case 0 -> {
                    System.out.println("Gracias por visitar nuestra tienda!!!");
                    salir = true;
                }

                default ->
                    System.out.println("Opción invalida.");
            }
        }
        teclado.close();
    }

}
