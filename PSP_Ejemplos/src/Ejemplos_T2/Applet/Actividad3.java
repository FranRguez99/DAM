package Ejemplos_T2.Applet;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Actividad3 extends Applet implements ActionListener {

    private HiloContador h1, h2;
    long CONTADOR1 = 0;
    long CONTADOR2 = 0;
    private boolean parar1;
    private boolean parar2;
    private Font fuente;
    private Button b1, b2, b3, b4;

    class HiloContador extends Thread{

        public void run() {
            parar1 = false;
            parar2 = false;
            Thread hiloActual = Thread.currentThread();
            while (h1 == hiloActual && !parar1) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
                CONTADOR1++;
            }
            while (h2 == hiloActual && !parar2) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
                CONTADOR2++;
            }
        }
    }

    public void init() {
        setBackground(Color.yellow);
        add(b1 = new Button("Iniciar contador 1"));
        b1.addActionListener(this);
        add(b2 = new Button("Parar contador 1"));
        b2.addActionListener(this);
        add(b3 = new Button("Iniciar contador 2"));
        b3.addActionListener(this);
        add(b4 = new Button("Parar contador 2"));
        b4.addActionListener(this);
        fuente = new Font("Verdana", Font.BOLD, 26);
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, 400, 400);
        g.setFont(fuente); // fuente
        g.drawString(Long.toString((long) CONTADOR1), 80, 100);
        g.drawString(Long.toString((long) CONTADOR2), 80, 140);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) // Pulso Iniciar contador o Continuar
        {
            b1.setLabel("Continuar");
            if (h1 != null && h1.isAlive()) {
            }          // Si el hilo está corriendo
            //  no hago nada.
            else {
                // creo hilo la primera vez y cuando finaliza el método run
                h1 = new HiloContador();
                h1.start();
            }
        } else if (e.getSource() == b2) {// Pulso Parar contador
            parar1 = true;
        }else if (e.getSource() == b3){
            b3.setLabel("Continuar");
            if (h2 != null && h2.isAlive()) {
            }          // Si el hilo está corriendo
            //  no hago nada.
            else {
                // creo hilo la primera vez y cuando finaliza el método run
                h2 = new HiloContador();
                h2.start();
            }
        } else if (e.getSource() == b4) {// Pulso Parar contador
            parar2 = true;
        }
    }

    public void stop() {
        h1 = null;
        h2 = null;
    }
}
