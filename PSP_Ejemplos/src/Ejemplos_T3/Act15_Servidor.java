package Ejemplos_T3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Act15_Servidor {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket servidor = new ServerSocket(11111);
        System.out.println("Esperando a cliente...");
        Socket cliente = servidor.accept();
        System.out.println("HA LLEGAO");

        // Entrada
        ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
        // Salida
        ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());

    }

}
