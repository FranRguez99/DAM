package Soluciones.act2_multicastSocketsUDP;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Cliente2 extends JFrame implements ActionListener, Runnable {

    static MulticastSocket ms = null;
    static byte[] buf = new byte[1000];
    static InetAddress grupo = null;
    static int Puerto = 12345;// Puerto multicast

    private JScrollPane scrollpane1cliente;
    static JTextArea textarea1cliente;
    JButton desconectarcliente = new JButton("Salir");
    boolean repetir = true;
    String nombrecliente;

    // constructor
    public Cliente2(String nom) {
        super(" VENTANA DE CHAT UDP - Nick: " + nom);
        setLayout(null);
        this.nombrecliente = nom;
        textarea1cliente = new JTextArea();
        scrollpane1cliente = new JScrollPane(textarea1cliente);
        scrollpane1cliente.setBounds(10, 50, 400, 300);
        add(scrollpane1cliente);
        desconectarcliente.setBounds(420, 50, 100, 30);
        add(desconectarcliente);

        textarea1cliente.setEditable(false);
        desconectarcliente.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }// fin constructor

    // accion cuando pulsamos botones
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == desconectarcliente) { // SE PULSA DESCONECTAR
            String texto = "*** Abandona el chat: " + nombrecliente + " ***";
            try {
                // ENVIANDO DESPEDIDA AL GRUPO
                DatagramPacket paquete = new DatagramPacket(texto.getBytes(),
                        texto.length(), grupo, Puerto);
                ms.send(paquete);
                ms.close();
                repetir = false;
                System.out.println("Abandona el chat: "+ nombrecliente);
                System.exit(0);

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }//

    // DESDE EL MÉTODO RUN SE RECIBEN LOS MENSAJES
    //Y SE PINTAN EN LA PANTALLA
    public void run() {
        while (repetir) {
            try {
                DatagramPacket p = new DatagramPacket(buf, buf.length);
                ms.receive(p);
                String texto = new String(p.getData(), 0, p.getLength());
                textarea1cliente.append(texto + "\n");
            }catch (SocketException s) {
                System.out.println(s.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) throws IOException {
        String nombre = JOptionPane.showInputDialog("Introduce tu nombre o nick:");
        // Se crea el socket multicast
        ms = new MulticastSocket(Puerto);
        grupo = InetAddress.getByName("225.0.0.1");// Grupo
        // Nos unimos al grupo
        ms.joinGroup(grupo);
        if (!nombre.trim().equals("")) {
            Cliente2 cliente = new Cliente2(nombre);
            cliente.setBounds(0, 0, 540, 400);
            cliente.setVisible(true);
            new Thread(cliente).start();

        } else {
            System.out.println("El nombre está vacío....");
        }
    }
}
