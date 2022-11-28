package ACT2;

public class HiloMensaje extends Thread implements Runnable{

    String mensaje;

    public HiloMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        try {
            sleep(getId()*100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Hola mundo! " + mensaje + " mi id es: " + this.getId());
    }
}
