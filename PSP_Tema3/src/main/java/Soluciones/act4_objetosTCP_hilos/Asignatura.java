package Soluciones.act4_objetosTCP_hilos;

import java.io.Serializable;
@SuppressWarnings("serial")

public class Asignatura implements Serializable {

    int id;
    String nombreAsig;

    public Asignatura(int id, String nombreAsig) {
        this.id = id;
        this.nombreAsig = nombreAsig;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreAsig() {
        return nombreAsig;
    }

    public void setNombreAsig(String nombreAsig) {
        this.nombreAsig = nombreAsig;
    }
}
