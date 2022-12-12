public class Hilo extends Thread{

    // Variables
    String nombre;
    Integer contador = 0;
    boolean parar = false;

    // Constructor de la clase
    public Hilo(String nombre) {
        this.nombre = nombre;
    }

    public String getContador() {
        return String.valueOf(contador);
    }

    // Acciones del hilo
    @Override
    public void run(){
        while(!parar){
            try {
                sleep(500);
                contador++;
                System.out.println(nombre + " valor: " + contador);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Método para detener el hilo
    public void paraHilo(){
        parar = true;
    }

}
