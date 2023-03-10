package Actividad2;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.Writer;


public class Actividad2
{
    static String servidor = "";
    static String puerto = "";
    static String usuario = "";
    static String clave = "";
    static String remitente = "";
    static String destinatario = "";
    static String asunto = "";
    static String cuerpo = "";
    static boolean conectado = false;

    public static void main(String[]args)
    {
        JFrame interfaz = new JFrame("Actividad 2 - PJRG");
        JButton conectarButton = new JButton("Conectar");
        JButton enviarButton = new JButton("Enviar mensaje");
        interfaz.add(conectarButton,BorderLayout.NORTH);
        interfaz.add(enviarButton,BorderLayout.SOUTH);

        AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();

        /* ************************************************************* */

        conectarButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                if(!conectado)
                {
                    if(iniciarSesion())
                    {
                        try
                        {
                            int respuesta;

                            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                            kmf.init(null, null);
                            KeyManager km = kmf.getKeyManagers()[0];

                            client.connect(servidor, Integer.parseInt(puerto));
                            client.setKeyManager(km);

                            respuesta = client.getReplyCode();

                            if(!SMTPReply.isPositiveCompletion(respuesta))
                            {
                                client.disconnect();
                                JOptionPane.showMessageDialog(null, "Error al conectar con el servidor");
                                System.exit(1);
                            }

                            client.ehlo(servidor);
                            JOptionPane.showMessageDialog(null, "Conectado correctamente");
                        }
                            catch (Exception e)
                            {
                                JOptionPane.showMessageDialog(null, "Error al conectar con el servidor");
                                System.exit(1);
                            }

                        conectarButton.setText("Desconectar");
                        conectado = true;
                    }
                }
                    else
                    {
                        try
                        {
                            client.disconnect();
                            System.exit(0);
                        }
                            catch (IOException f)
                            {
                                f.printStackTrace();
                            }
                    }
            }
        });

        enviarButton.addActionListener(actionEvent -> {
            try
            {
                if(client.execTLS())
                {
                    if (client.auth(AuthenticatingSMTPClient.AUTH_METHOD.LOGIN, usuario, clave))
                    {
                        SimpleSMTPHeader cabecera = new SimpleSMTPHeader(remitente, destinatario, asunto);

                        client.setSender(remitente);
                        client.addRecipient(destinatario);

                        Writer writer = client.sendMessageData();
                        if (writer == null)
                        {
                            JOptionPane.showMessageDialog(null, "Fallo al enviar los datos");
                            System.exit(1);
                        }

                        writer.write(cabecera.toString());
                        writer.write(cuerpo);
                        writer.close();

                        boolean exito = client.completePendingCommand();

                        if(!exito)
                        {
                            JOptionPane.showMessageDialog(null, "Fallo al finalizar la transaccion");
                            System.exit(1);
                        }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Mensaje enviado con exito");
                            }
                    }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Usuario no autenticado");
                        }

                }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Fallo al ejecutar START TLS");
                    }
            }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
        });

        /* ************************************************************* */

        interfaz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        interfaz.setSize(300, 100);
        interfaz.setResizable(false);
        interfaz.setVisible(true);
    }

    public static boolean iniciarSesion()
    {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel labelPanel = new JPanel(new GridLayout(0, 1, 2, 2));
        labelPanel.add(new JLabel("Servidor : ", SwingConstants.RIGHT));
        labelPanel.add(new JLabel("Puerto : ", SwingConstants.RIGHT));
        labelPanel.add(new JLabel("Usuario : ", SwingConstants.RIGHT));
        labelPanel.add(new JLabel("", SwingConstants.RIGHT));
        labelPanel.add(new JLabel("Clave : ", SwingConstants.RIGHT));
        labelPanel.add(new JLabel("Remitente : ", SwingConstants.RIGHT));
        labelPanel.add(new JLabel("Destinatario : ", SwingConstants.RIGHT));
        labelPanel.add(new JLabel("Asunto : ", SwingConstants.RIGHT));
        labelPanel.add(new JLabel("Cuerpo : ", SwingConstants.RIGHT));

        panel.add(labelPanel, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField servidortTextField = new JTextField();
        controls.add(servidortTextField);
        JTextField puertoTextField = new JTextField();
        controls.add(puertoTextField);
        JTextField usuarioTextField = new JTextField();
        controls.add(usuarioTextField);
        Label vacio = new Label("");
        controls.add(vacio);
        JTextField claveTextField = new JTextField();
        controls.add(claveTextField);
        JTextField remitenteTextField = new JTextField();
        controls.add(remitenteTextField);
        JTextField destinatarioTextField = new JTextField();
        controls.add(destinatarioTextField);
        JTextField asuntoTextField = new JTextField();
        controls.add(asuntoTextField);
        JTextField cuerpoTextField = new JTextField();
        controls.add(cuerpoTextField);
        panel.add(controls, BorderLayout.CENTER);

        int n = JOptionPane.showConfirmDialog(frame, panel, "Introducir datos SMTP - PJRG", JOptionPane.OK_CANCEL_OPTION);

        if(n == JOptionPane.OK_OPTION)
        {
            servidor = servidortTextField.getText();
            puerto = puertoTextField.getText();
            usuario = usuarioTextField.getText();
            clave = claveTextField.getText();
            remitente = remitenteTextField.getText();
            destinatario = destinatarioTextField.getText();
            asunto = asuntoTextField.getText();
            cuerpo = cuerpoTextField.getText();

            return true;
        }
        else if(n == JOptionPane.CANCEL_OPTION)
        {
            return false;
        }

        return false;
    }
}