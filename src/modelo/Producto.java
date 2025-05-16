package modelo;

public class Producto {
    private static int contadorId = 1; // Variable estática para generar ID automáticamente
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.id = contadorId++;  // Asigna el ID y luego incrementa el contador
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
    
    public void mostrarInfo(){
        System.out.printf("""
                          Id: %s
                          Nombre: %s
                          Precio: %s
                          Stock: %s
                          """, this.id, this,nombre, this.precio, this.stock);
    }

    // Getters y setters
    public int getId() { return this.id; }
    public String getNombre() { return this.nombre; }
    public double getPrecio() { return this.precio; }
    public int getStock() { return this.stock;}

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setStock(int stock) {this.stock = stock ; }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre= " + nombre + ", precio= " + precio + ", stock= " + stock + '}';
    }

}


