import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

public class AppletExamen extends Applet implements Runnable, ActionListener {

    // Atributos
    Button bComenzar, bHilo1, bHilo2, bFinalizar;
    Thread h;
    Hilo hilo1, hilo2;
    Font fuente;
    Integer contador1 = 0;
    Integer contador2 = 0;
    boolean parar = false;

    public void start() {

    }

    public void init() {
        setBackground(Color.CYAN);
        add(bComenzar = new Button("Comenzar"));
        bComenzar.addActionListener(this);
        add(bHilo1 = new Button("Interrumpir Hilo 1"));
        bHilo1.addActionListener(this);
        add(bHilo2 = new Button("Interrumpir Hilo 2"));
        bHilo2.addActionListener(this);
        add(bFinalizar = new Button("Finalizar"));
        bFinalizar.addActionListener(this);
        fuente = new Font("Calibri", Font.BOLD, 30);
    }

    // Pinta la pantalla
    public void paint(Graphics g) {
        g.clearRect(0, 0, 400, 400);
        g.setFont(fuente);
        g.drawString("Hilo 1    " + contador1, 50, 100);
        g.drawString("Hilo 2    " + contador2, 50, 200);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bComenzar) {
            if ((hilo1 != null && hilo1.isAlive()) && (hilo2 != null && hilo2.isAlive())) {
                // Nada
            } else {
                h = new Thread(this);
                hilo1 = new Hilo("Hilo 1");
                hilo2 = new Hilo("Hilo 2");
                h.start();
                hilo1.start();
                hilo2.start();
                bComenzar.setEnabled(false);
            }
        } else if (e.getSource() == bHilo1) {
            contador1 = Integer.valueOf(hilo1.getContador());
            hilo1.paraHilo();
        } else if (e.getSource() == bHilo2) {
            contador2 = Integer.valueOf(hilo2.getContador());
            hilo2.paraHilo();
        } else if (e.getSource() == bFinalizar) {
            if(hilo1.isAlive()){
                contador1 = Integer.valueOf(hilo1.getContador());
                hilo1.paraHilo();
            }
            if(hilo2.isAlive()){
                contador2 = Integer.valueOf(hilo2.getContador());
                hilo2.paraHilo();
            }
            parar = true;
            System.out.println("Valor del hilo 1: " + contador1);
            System.out.println("Valor del hilo 2: " + contador2);
        }

    }

    @Override
    public void run() {
        Thread hiloActual = Thread.currentThread();
        while(h == hiloActual && !parar){
            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            repaint();
        }
        repaint();
    }
}
