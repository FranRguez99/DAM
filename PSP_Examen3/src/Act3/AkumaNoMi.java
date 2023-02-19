package Act3;

import java.io.Serializable;

public class AkumaNoMi implements Serializable {

    String nombre;
    String descripcion;
    String apariencia;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getApariencia() {
        return apariencia;
    }

    public void setApariencia(String apariencia) {
        this.apariencia = apariencia;
    }

    public AkumaNoMi(String nombre, String descripcion, String apariencia) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.apariencia = apariencia;
    }
}
