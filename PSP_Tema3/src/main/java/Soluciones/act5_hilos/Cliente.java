package Soluciones.act5_hilos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Cliente {

    // Envía una cadena al servidor con el botón Enviar
    // El botón Limpiar limpia los dos campos
    // El botón salir envía un * al servidor y finaliza la ejecución

    public static void main(String[] args) throws IOException {

        String host = "localhost";
        int puerto = 44444;
        Socket cliente = new Socket(host, puerto);

        PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true); // creo flujo salida
        //Scanner in = new Scanner(System.in);
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // creo como un Scanner
        BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream())); // creo flujo entrada

        //String cadenaEnviada, cadenaRecibida = "";


        // -----------------------INTERFAZ------------------------

        JFrame marco = new JFrame("CLIENTE");
        marco.setLayout(new FlowLayout());
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField fieldEnvio = new JTextField();
        //fieldEnvio.setBorder(new EmptyBorder(5, 10, 5, 10));
        fieldEnvio.setPreferredSize(new Dimension(365, 25));

        JTextField fieldRecibo = new JTextField();
        fieldRecibo.setEditable(false);
        fieldRecibo.setPreferredSize(new Dimension(365, 25));

        marco.add(fieldEnvio, BorderLayout.NORTH);
        marco.add(fieldRecibo, BorderLayout.CENTER);

        JPanel panel = new JPanel();

        JButton botEnviar, botLimpiar, botSalir;
        botEnviar = new JButton("Enviar");
        botLimpiar = new JButton("Limpiar");
        botSalir = new JButton("Salir");

        ActionListener pulsado = new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(e.getSource() == botEnviar) {
                    String cadenaEnviada = fieldEnvio.getText();
                    salida.println(cadenaEnviada);
                    if (!cadenaEnviada.trim().equals("*")) {
                        try {
                            String cadenaRecibida = entrada.readLine();
                            fieldRecibo.setText(cadenaRecibida);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }

                if(e.getSource() == botLimpiar) {
                    fieldEnvio.setText("");
                    fieldRecibo.setText("");
                }

                if(e.getSource() == botSalir) {
                    String cadenaEnviada = "*";
                    salida.println(cadenaEnviada);
                    salida.close();
                    try {
                        entrada.close();
                        cliente.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    System.exit(0);
                }

            }
        };

        panel.add(botEnviar, BorderLayout.NORTH);
        panel.add(botLimpiar, BorderLayout.SOUTH);
        panel.add(botSalir, BorderLayout.EAST);

        botEnviar.addActionListener(pulsado);
        botLimpiar.addActionListener(pulsado);
        botSalir.addActionListener(pulsado);

        marco.add(panel);
        marco.setSize(400, 150);
        marco.setResizable(false);
        marco.setVisible(true);
        marco.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // -------------------------------------------------------

        /*do {
            System.out.print("Introduce una cadena: ");
            //cadenaEnviada = in.nextLine();
            cadenaEnviada = in.readLine();
            salida.println(cadenaEnviada);
            if (!cadenaEnviada.trim().equals("*")) {
                cadenaRecibida = entrada.readLine();
                System.out.println("\tRecibido: " + cadenaRecibida);
            }
        } while (!cadenaEnviada.trim().equals("*"));

        salida.close();
        entrada.close();
        in.close();
        cliente.close();*/
    }

}
