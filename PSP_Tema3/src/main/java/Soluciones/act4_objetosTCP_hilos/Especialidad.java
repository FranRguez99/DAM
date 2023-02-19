package Soluciones.act4_objetosTCP_hilos;

import java.io.Serializable;
@SuppressWarnings("serial")

public class Especialidad implements Serializable {

    int id;
    String nombreEspe;

    public Especialidad () {
        id = 0;
        nombreEspe = "";
    }

    public Especialidad(int id, String nombreEspe) {
        this.id = id;
        this.nombreEspe = nombreEspe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEspe() {
        return nombreEspe;
    }

    public void setNombreEspe(String nombreEspe) {
        this.nombreEspe = nombreEspe;
    }
}
