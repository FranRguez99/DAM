package ACT5;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppletLetra extends Applet implements Runnable, ActionListener {

    private Button b1;
    private Thread h;
    private boolean izquierda, parar;
    private Font fuente;
    private int x = 1;

    public void start() {
    }

    public void init() {
        setBackground(Color.yellow);
        add(b1 = new Button("Iniciar hilo"));
        b1.addActionListener(this);
        fuente = new Font("Verdana", Font.BOLD, 26);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){
            b1.setLabel("Finalizar hilo");
            if (h != null && h.isAlive()){
                b1.setLabel("Reanudar hilo");
                parar = true;
            } else {
                h = new Thread(this);
                h.start();
            }
        }

    }

    @Override
    public void run() {
        parar = false;
        izquierda = false;
        Thread hiloActual = Thread.currentThread();
        while(h == hiloActual && !parar){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(!izquierda){
                System.out.println(x);
                x = x+1;
                repaint();
            } else {
                System.out.println(x);
                x = x-1;
                repaint();
            }
            if (x == getSize().width){
                izquierda = true;
            } else if (x == 0){
                izquierda = false;
            }

        }
    }

    public void paint(Graphics g){
        g.clearRect(0, 0, 400, 400);
        g.setFont(fuente); // fuente
        g.drawString("O", x, 100);
    }

    public void stop() {
        h = null;
    }

}
