package Clases;

import java.util.ArrayList;
import java.util.List;

public class Cartelera {
    private int id;
    private int cineId;
    private List<Funcion> funciones;

    public Cartelera() {
        this.funciones = new ArrayList<>();
    }

    public Cartelera(int id, int cineId) {
        this.id = id;
        this.cineId = cineId;
        this.funciones = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCineId() {
        return cineId;
    }

    public void setCineId(int cineId) {
        this.cineId = cineId;
    }

    public List<Funcion> getFunciones() {
        return funciones;
    }

    public void agregarFuncion(Funcion nuevaFuncion) {
        funciones.add(nuevaFuncion);
    }

    public void mostrar() {
        System.out.println("Cartelera ID: " + id + " - Cine ID: " + cineId);
        if (funciones.isEmpty()) {
            System.out.println("No hay funciones registradas.");
        } else {
            for (Funcion funcion : funciones) {
                funcion.mostrar();
            }
        }
    }
}
