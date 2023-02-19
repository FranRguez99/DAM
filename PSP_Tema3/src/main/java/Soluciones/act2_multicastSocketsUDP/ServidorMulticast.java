package Soluciones.act2_multicastSocketsUDP;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class ServidorMulticast extends JFrame implements ActionListener, Runnable {

    static MulticastSocket ms = null;
    static byte[] buf = new byte[1000];
    static InetAddress grupo = null;
    static int Puerto = 12345;// Puerto multicast

    static JTextField mensaje = new JTextField();
    private JScrollPane scrollpane1;
    static JTextArea textarea1;
    JButton boton = new JButton("Enviar");
    JButton desconectar = new JButton("Salir");
    boolean repetir = true;
    String nombre = "Servidor";

    // constructor

    public ServidorMulticast() {
        super("SERVIDOR");
        setLayout(null);
        mensaje.setBounds(10, 10, 400, 30);
        add(mensaje);
        textarea1 = new JTextArea();
        scrollpane1 = new JScrollPane(textarea1);
        scrollpane1.setBounds(10, 50, 400, 300);
        add(scrollpane1);
        boton.setBounds(420, 10, 100, 30);
        add(boton);
        desconectar.setBounds(420, 50, 100, 30);
        add(desconectar);

        textarea1.setEditable(false);
        boton.addActionListener(this);
        desconectar.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws IOException {
        // Se crea el socket multicast
        ms = new MulticastSocket(Puerto);
        grupo = InetAddress.getByName("225.0.0.1");// Grupo
        // Nos unimos al grupo
        ms.joinGroup(grupo);
        ServidorMulticast server = new ServidorMulticast();
        server.setBounds(0, 0, 540, 400);
        server.setVisible(true);
        new Thread(server).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boton) { // SE PULSA EL ENVIAR
            String texto = nombre + ">> " + mensaje.getText();
            mensaje.setText("");
            try {
                // ENVIANDO mensaje al grupo
                DatagramPacket paquete = new DatagramPacket(texto.getBytes(), texto.length(), grupo, Puerto);
                ms.send(paquete);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getSource() == desconectar) { // SE PULSA DESCONECTAR
            String texto = "*** Abandona el chat: " + nombre + " ***";
            try {
                // ENVIANDO DESPEDIDA AL GRUPO
                DatagramPacket paquete = new DatagramPacket(texto.getBytes(),
                        texto.length(), grupo, Puerto);
                ms.send(paquete);
                ms.close();
                repetir = false;
                System.out.println("Abandona el chat: " + nombre);
                System.exit(0);

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    // DESDE EL MÉTODO RUN SE RECIBEN LOS MENSAJES Y SE PINTAN EN LA PANTALLA
    @Override
    public void run() {
        while (repetir) {
            try {
                DatagramPacket p = new DatagramPacket(buf, buf.length);
                ms.receive(p);
                String texto = new String(p.getData(), 0, p.getLength());
                textarea1.append(texto + "\n");
            }catch (SocketException s) {
                System.out.println(s.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
