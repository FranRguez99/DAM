import java.util.Objects;

public class Consumidor extends Thread{

    // Atributos
    Cola c;
    String nombre;
    String letra;
    Descifrador d;

    public Consumidor(Cola c, String nombre, Descifrador d) {
        this.c = c;
        this.nombre = nombre;
        this.d = d;
    }

    @Override
    public void run(){
        while(true){
            try {
                sleep(5);
                letra = c.get();
                d.addLetra(letra);
                System.out.println("El " + nombre + " lee la letra: " + letra);
                sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
