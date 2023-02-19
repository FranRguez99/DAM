package Act3;

import java.io.Serializable;

public class Mugiwara implements Serializable {

    String nombre;
    String rol;
    AkumaNoMi akumaNoMi;
    Barco barco;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public AkumaNoMi getAkumaNoMi() {
        return akumaNoMi;
    }

    public void setAkumaNoMi(AkumaNoMi akumaNoMi) {
        this.akumaNoMi = akumaNoMi;
    }

    public Barco getBarco() {
        return barco;
    }

    public void setBarco(Barco barco) {
        this.barco = barco;
    }

    public Mugiwara(String nombre, String rol, AkumaNoMi akumaNoMi, Barco barco) {
        this.nombre = nombre;
        this.rol = rol;
        this.akumaNoMi = akumaNoMi;
        this.barco = barco;
    }

    public Mugiwara() {
        super();
    }
}
