package safa.fran.psp_ud2_act9;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Hilo extends Thread{

    String nombre;
    long espera;
    TextField tf;
    boolean interrumpir = false;
    Integer cuenta = 0;

    public Hilo(String nombre, long espera, TextField tf) {
        this.nombre = nombre;
        this.espera = espera;
        this.tf = tf;
    }

    public void setInterrumpir(boolean interrumpir) {
        this.interrumpir = interrumpir;
    }

    @Override
    public void run(){
        // Bucle principal del hilo
        while (!Thread.currentThread().isInterrupted()) {
            // Realizar alguna tarea...
            if(interrumpir){
                try {
                    sleep(espera);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                interrumpir = false;
            }
            try {
                sleep(100);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            tf.setText(String.valueOf(cuenta));
            cuenta++;
            // Comprobar la interrupción del hilo
            if (Thread.currentThread().isInterrupted()) {
                // Limpiar el estado del hilo y finalizar
                break;
            }
        }
    }

    public String getCuenta() {
        return String.valueOf(cuenta);
    }
}
