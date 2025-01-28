package Clases;

public class Empleado {
    private int id;
    private String nombre;
    private String puesto;
    private double salario;

    public Empleado() {
        this.id = 1;
        this.nombre = "Pepito";
        this.puesto = "Cajero";
        this.salario = 2400;
    }

    public Empleado(int id, String nombre, String puesto, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
    }
    
    public Empleado(int id) {
        this.id = id;
        this.nombre = "Pepito";
        this.puesto = "Cajero";
        this.salario = 2000;
    }


    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public double getSalario() {
        return salario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void mostrar() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Puesto: " + puesto);
        System.out.println("Salario: $" + salario);
    }
}
