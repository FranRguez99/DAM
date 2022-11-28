package Ejemplos_T2.Applet;

public class Act4_SolicitaSuspender {

    private boolean suspender;

    public synchronized void set(boolean b){
        suspender = b;
        notifyAll();
    }

    public synchronized void esperandoParaReanudar() throws InterruptedException{
        while(suspender){
            wait(); // Espera recibir un notify() o notifyAll()
        }
    }

}
