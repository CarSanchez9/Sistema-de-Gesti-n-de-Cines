package Clases;

public class Producto {
    private int id;
    private int candibarId;
    private String nombre;
    private String tipo;
    private double precio;

    public Producto() {}

    public Producto(int id, int candibarId, String nombre, String tipo, double precio) {
        this.id = id;
        this.candibarId = candibarId;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCandibarId() {
        return candibarId;
    }

    public void setCandibarId(int candibarId) {
        this.candibarId = candibarId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void mostrar() {
        System.out.println("ID: " + id);
        System.out.println("Candibar ID: " + candibarId);
        System.out.println("Nombre: " + nombre);
        System.out.println("Tipo: " + tipo);
        System.out.println("Precio: $" + precio);
    }
}
