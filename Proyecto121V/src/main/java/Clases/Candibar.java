package Clases;

public class Candibar {
    private int id;
    private int cineId;
    private int empleadoEncargadoId;

    public Candibar() {}

    public Candibar(int id, int cineId, int empleadoEncargadoId) {
        this.id = id;
        this.cineId = cineId;
        this.empleadoEncargadoId = empleadoEncargadoId;
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

    public int getEmpleadoEncargadoId() {
        return empleadoEncargadoId;
    }

    public void setEmpleadoEncargadoId(int empleadoEncargadoId) {
        this.empleadoEncargadoId = empleadoEncargadoId;
    }
}
