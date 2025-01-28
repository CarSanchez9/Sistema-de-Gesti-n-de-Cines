package Clases;

import java.sql.Date;
import java.sql.Time;

public class Funcion {
    private int id;
    private int idCartelera;
    private int idPelicula;
    private int idSala;
    private Date fecha;
    private Time hora;

    public Funcion(int id, int idCartelera, int idPelicula, int idSala, Date fecha, Time hora) {
        this.id = id;
        this.idCartelera = idCartelera;
        this.idPelicula = idPelicula;
        this.idSala = idSala;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Funcion() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCartelera() {
        return idCartelera;
    }

    public void setIdCartelera(int idCartelera) {
        this.idCartelera = idCartelera;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public void mostrar() {
        System.out.println("ID: " + id);
        System.out.println("ID Cartelera: " + idCartelera);
        System.out.println("ID Película: " + idPelicula);
        System.out.println("ID Sala: " + idSala);
        System.out.println("Fecha: " + fecha);
        System.out.println("Hora: " + hora);
    }
}
