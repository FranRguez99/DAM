package com.example.psp_examen3_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.*;
import java.util.ResourceBundle;

public class ClienteController implements Initializable {

    // Atributos multicast
    static MulticastSocket ms = null;
    static byte[] buffer = new byte[1000];
    static InetAddress grupo = null;
    static int puerto = 12345;
    boolean bucle = true;
    String nombreUsuario;


    @FXML
    private Button btnEnviar;

    @FXML
    private Button btnRefrescar;

    @FXML
    private Button btnSalir;

    @FXML
    private Pane paneChat;

    @FXML
    private Pane paneInicio;

    @FXML
    private TextArea taChat;

    @FXML
    private TextField tfUsuario;

    @FXML
    private TextField tfEnviar;

    @FXML
    void acceso(ActionEvent event) {
        nombreUsuario = tfUsuario.getText();
        paneInicio.setVisible(false);
        paneChat.setVisible(true);
    }

    @FXML
    void enviar(ActionEvent event) throws IOException {
        String texto = nombreUsuario + ">> " + tfEnviar.getText();
        DatagramPacket paquete = new DatagramPacket(texto.getBytes(), texto.length(), grupo, puerto);
        ms.send(paquete);
    }

    @FXML
    void refrescar(ActionEvent event) {
        try {
            DatagramPacket p = new DatagramPacket(buffer, buffer.length);
            ms.receive(p);
            String texto = new String(p.getData(), 0, p.getLength());
            taChat.textProperty().set(taChat.getText() + texto + "\n");
        }catch (SocketException s) {
            System.out.println(s.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void salir(ActionEvent event) {
        System.exit(1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ms = new MulticastSocket(puerto);
            grupo = InetAddress.getByName("225.0.0.1");
            ms.joinGroup(grupo);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
