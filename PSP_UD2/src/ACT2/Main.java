package ACT2;

public class Main {

    public static void main(String[] args){

        HiloMensaje h1 = new HiloMensaje("Mensaje 1");
        HiloMensaje h2 = new HiloMensaje("Mensaje 2");
        HiloMensaje h3 = new HiloMensaje("Mensaje 3");
        HiloMensaje h4 = new HiloMensaje("Mensaje 4");
        HiloMensaje h5 = new HiloMensaje("Mensaje 5");

        Thread t1 = new Thread(h1);
        Thread t2 = new Thread(h2);
        Thread t3 = new Thread(h3);
        Thread t4 = new Thread(h4);
        Thread t5 = new Thread(h5);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }

}
