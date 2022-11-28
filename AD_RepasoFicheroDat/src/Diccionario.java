import java.io.Serializable;
public class Diccionario implements Serializable {

    String palabra;
    String definicion;

    // Getters & setters
    public String getPalabra() {
        return palabra;
    }

    public String getDefinicion() {
        return definicion;
    }

    @Override
    public String toString() {
        return "Diccionario{" +
                "palabra='" + palabra + '\'' +
                ", definicion='" + definicion + '\'' +
                '}';
    }

    // Constructor
    public Diccionario(String palabra, String definicion) {
        this.palabra = palabra;
        this.definicion = definicion;
    }
}
