package Examen;

public class Barco {

    String nombre;
    String tripulantes;
    String tipo;
    Integer altura;
    Integer longitud;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Barco(String nombre, String tripulantes, String tipo, Integer altura, Integer longitud) {
        this.nombre = nombre;
        this.tripulantes = tripulantes;
        this.tipo = tipo;
        this.altura = altura;
        this.longitud = longitud;
    }
}
