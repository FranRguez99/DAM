package Act3;

import java.io.Serializable;

public class Barco implements Serializable {

    String barco;
    String tripulantes;
    String tipo;
    Integer altura;
    Integer longitud;

    public String getBarco() {
        return barco;
    }

    public void setBarco(String barco) {
        this.barco = barco;
    }

    public String getTripulantes() {
        return tripulantes;
    }

    public void setTripulantes(String tripulantes) {
        this.tripulantes = tripulantes;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public Integer getLongitud() {
        return longitud;
    }

    public void setLongitud(Integer longitud) {
        this.longitud = longitud;
    }

    public Barco(String barco, String tripulantes, String tipo, Integer altura, Integer longitud) {
        this.barco = barco;
        this.tripulantes = tripulantes;
        this.tipo = tipo;
        this.altura = altura;
        this.longitud = longitud;
    }
}
