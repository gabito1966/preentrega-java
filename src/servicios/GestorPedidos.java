package servicios;

import modelo.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GestorPedidos {

    private List<Pedido> pedidos;
    private int contadorPedidos = 1;

    public GestorPedidos() {
        pedidos = new ArrayList<>();
    }

    public Pedido crearPedido() {
        Pedido nuevo = new Pedido(contadorPedidos++);
        pedidos.add(nuevo);
        return nuevo;
    }

    public List<Pedido> listarPedidos() {
        return pedidos;
    }

    public Pedido buscarPedidoPorId(int id) {
        return pedidos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Producto> productosMasVendidos() {
        // Creamos un mapa para contar las veces que un producto se agrega a los pedidos
        Map<Producto, Integer> contadorProductos = new HashMap<>();
        for (Pedido pedido : pedidos) {
            for (ItemPedido item : pedido.getItems()) {
                contadorProductos.put(item.getProducto(),
                        contadorProductos.getOrDefault(item.getProducto(), 0) + item.getCantidad());
            }
        }

        // Ordenamos los productos por cantidad descendente
        return contadorProductos.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
