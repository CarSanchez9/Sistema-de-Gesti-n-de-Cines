package Clases;

public class Sala {
    private int id;
    private int nroSala;
    private int nroFunciones;
    private Funcion [] func = new Funcion [nroFunciones]; 
    private int nroTotalAsientos;
    private String tipo;
    public Sala(int id, int nroSala, String tipo, int nroTotalAsientos) {
        this.id = id;
        this.nroSala = nroSala;
        this.tipo = tipo;
        this.nroTotalAsientos = nroTotalAsientos;
        this.func = new Funcion[20]; 
    }
    public Sala() {
        this.id = 0;
        this.nroSala = 0;
        this.tipo = "";
        this.nroTotalAsientos = 0; 
        this.func = new Funcion[20];
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNroSala() {
        return nroSala;
    }

    public void setNroSala(int nroSala) {
        this.nroSala = nroSala;
    }

    public Funcion[] getFunciones() {
        return func;
    }

    public void setFunciones(Funcion[] func) {
        this.func = func;
    }

    public int getNroTotalAsientos() {
        return nroTotalAsientos;
    }

    public void setNroTotalAsientos(int nroTotalAsientos) {
        this.nroTotalAsientos = nroTotalAsientos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public  void addSala(Sala[] salas, Sala nuevaSala) {
        for (int i = 0; i < salas.length; i++) {
            if (salas[i] == null) {
                salas[i] = nuevaSala;
                System.out.println("Sala agregada: Sala " + nuevaSala.getNroSala());
                return;
            }
        }
        System.out.println("no se puede agregar mas salas");
    }
    public  void mostrarSalas(Sala[] salas) {
        System.out.println("Salas registradas:");
        for (int i = 0; i < salas.length; i++) {
            if (salas[i] != null) {
                System.out.println("Sala "+salas[i].getNroSala()+ " (" +salas[i].getTipo()+ ")");
                System.out.println("numero total de asientos: "+salas[i].getNroTotalAsientos());
            }
        }
    }
    public  void quitarSala(Sala[] salas, int indice) {
        if (indice >= 0 && indice < salas.length && salas[indice] != null) {
            System.out.println("Quitando sala: Sala " +salas[indice].getNroSala());
            for (int i = indice; i < salas.length - 1; i++) {
                salas[i] = salas[i + 1];
            }
            salas[salas.length - 1] = null; 
        } else {
            System.out.println("no se pude quitar sala");
        }
    }
}



