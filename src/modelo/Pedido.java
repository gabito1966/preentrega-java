package modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private static int contadorId = 1;  // Contador estático para generar ID de pedido
    private int id;
    private List<ItemPedido> items;

    public Pedido(int par) {
        this.id = contadorId++;  // Asigna el ID y luego incrementa el contador
        this.items = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<ItemPedido> getItems() {
        return this.items;
    }

    public void agregarItem(ItemPedido item) {
        items.add(item);
    }

    public double calcularTotal() {
        return items.stream().mapToDouble(ItemPedido::calcularSubtotal).sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pedido ID: " + id + "\n");
        for (ItemPedido item : items) {
            sb.append(item.toString()).append("\n");
        }
        sb.append("Total: $").append(calcularTotal());
        return sb.toString();
    }
}
