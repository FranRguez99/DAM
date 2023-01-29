package Ejemplos_T3;

import java.io.Serializable;

public class Act14_Numeros implements Serializable {

    int numero;
    long cuadrado;
    Long cubo;

    public Act14_Numeros() {
    }

    @Override
    public String toString() {
        return "Act14_Numeros{" +
                "numero=" + numero +
                ", cuadrado=" + cuadrado +
                ", cubo=" + cubo +
                '}';
    }

    public Act14_Numeros(int numero, long cuadrado, Long cubo) {
        this.numero = numero;
        this.cuadrado = cuadrado;
        this.cubo = cubo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public long getCuadrado() {
        return cuadrado;
    }

    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    public Long getCubo() {
        return cubo;
    }

    public void setCubo(Long cubo) {
        this.cubo = cubo;
    }
}
