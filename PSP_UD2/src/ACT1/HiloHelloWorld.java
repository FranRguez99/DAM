package ACT1;

public class HiloHelloWorld extends Thread{

    @Override
    public void run() {
        System.out.println("Hola mundo! Soy el hilo " + this.getId());
    }
}
